package jin.chen.service.imp;

import com.alibaba.druid.support.json.JSONUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jin.chen.enums.BgmOperatorEnum;
import jin.chen.mapper.BgmMapper;
import jin.chen.mapper.UsersReportCustomerMapper;
import jin.chen.mapper.UsersReportMapper;
import jin.chen.mapper.VideosMapper;
import jin.chen.pojo.*;
import jin.chen.pojo.vo.Reports;
import jin.chen.service.VideoService;
import jin.chen.util.ZKCurator;
import jin.chen.utils.PagedResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class VideoServiceImp implements VideoService {

    @Autowired
    private BgmMapper bgmMapper;

    @Autowired
    private ZKCurator zkCurator;

    @Autowired
    private UsersReportCustomerMapper usersReportCustomerMapper;

    @Autowired
    private VideosMapper videosMapper;

    @Override
    public void addBgm(Bgm bgm) {
        String id = UUID.randomUUID().toString().replaceAll("-", "");
        bgm.setId(id);
        bgmMapper.insertSelective(bgm);
        String path = "";
        try {
            path = URLEncoder.encode(bgm.getPath(), "UTF-8");
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        Map<String, String> map = new HashMap<>();
        map.put("operatorType", BgmOperatorEnum.ADD.type);
        map.put("path", path );
        zkCurator.operatorBgm(id, JSONUtils.toJSONString(map));
    }

    @Override
    public PagedResult queryBgmList(Integer page, Integer pageSize) {
        //开始分页
        PageHelper.startPage(page, pageSize);
        //查询所有视频信息
        BgmExample example = new BgmExample();
        List<Bgm> list=  bgmMapper.selectByExample(example);
//        List<Bgm> list= bgmMapper.selectAll();

        PageInfo<Bgm> info = new PageInfo<>(list);
        PagedResult pagedResult = new PagedResult();
        //当前页数
        pagedResult.setPage(page);
        //总页数
        pagedResult.setTotal(info.getPages());
        //每行显示的内容
        pagedResult.setRows(list);
        //总记录数
        pagedResult.setRecords(info.getTotal());
        return pagedResult;
    }

    @Override
    public void deleteBgm(String bgmId) {
        Bgm bgm = bgmMapper.selectByPrimaryKey(bgmId);
        bgmMapper.deleteByPrimaryKey(bgmId);
        Map<String, String> map = new HashMap<>();
        map.put("operatorType", BgmOperatorEnum.DELETE.type);
        map.put("path", bgm.getPath());
        zkCurator.operatorBgm(bgmId, JSONUtils.toJSONString(map));
    }

    @Override
    public PagedResult queryAllVideos(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Reports> list = usersReportCustomerMapper.queryAllVideos();
        PageInfo<Reports> info = new PageInfo<>(list);
        PagedResult result = new PagedResult();
        result.setPage(page);
        result.setTotal(info.getPages());
        result.setRows(list);
        result.setRecords(info.getTotal());
        return result;
    }

    @Override
    public void forbitVideo(String videoId, Integer status) {
        Videos videos = new Videos();
        videos.setId(videoId);
        videos.setStatus(status);
        videosMapper.updateByPrimaryKeySelective(videos);
    }
}
