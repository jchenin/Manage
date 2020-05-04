package jin.chen.controller;

import jin.chen.base.BaseController;
import jin.chen.bean.AdminUser;
import jin.chen.enums.VideoStatusEnum;
import jin.chen.pojo.Bgm;
import jin.chen.pojo.Users;
import jin.chen.pojo.Videos;
import jin.chen.service.VideoService;
import jin.chen.utils.PagedResult;
import jin.chen.utils.VideoJSONResult;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.portable.ValueInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.UUID;

@Controller
@RequestMapping("video")
public class VideoController{

    @Autowired
    private VideoService videoService;

    @Value("${FILE_SPACE}")
    private String FILE_SPACE;

    @GetMapping("/showAddBgm")
    public String addBgm() {
        return "video/addBgm";
    }

    @GetMapping("/showBgmList")
    public String bgmList(){
        return "video/bgmList";
    }

    @GetMapping("/showReportList")
    public String showReportList(){
        return "video/reportList";
    }

    /**
     * 查询背景音乐列表接口
     * @param page  当前页数
     * @return
     */
    @PostMapping("/queryBgmList")
    @ResponseBody
    public PagedResult queryBgmList(Integer page){
        if(page == null){
            page = 1;
        }
        //默认一页显示十条数据
        PagedResult result = videoService.queryBgmList(page, 10);
        return result;
    }

    /**
     * 删除背景音乐
     * @param bgmId
     * @return
     */
    @PostMapping("/deleteBgm")
    @ResponseBody
    public VideoJSONResult deleteBgm(String bgmId){
        videoService.deleteBgm(bgmId);
        return VideoJSONResult.ok();
    }

    @PostMapping("/bgmUpload")
    @ResponseBody
    public VideoJSONResult bgmUpload( @RequestParam("file") MultipartFile[] files){
        //确定命名空间,环境不同，可在resource中配置
//        String finalSpace = "E:" +  File.separator +"videos-dev" + File.separator + "mvc-bgm";
        //保存数据库的相对路径
        String UploadDBPath = File.separator+ "bgms";
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        try {
            if (files != null && files.length > 0) {
                String fileName = files[0].getOriginalFilename();
                if (org.apache.commons.lang3.StringUtils.isNotBlank(fileName)) {
                    //文件保存的绝对路径
                    String finalFilePath = FILE_SPACE + UploadDBPath + File.separator + fileName;
                    UploadDBPath += (File.separator + fileName);
                    File finalFile = new File(finalFilePath);
                    if (finalFile.getParentFile() != null || !finalFile.getParentFile().isDirectory()) {
                        //创建父文件夹
                        finalFile.getParentFile().mkdirs();
                    }
                    fileOutputStream = new FileOutputStream(finalFile);
                    inputStream = files[0].getInputStream();
                    IOUtils.copy(inputStream, fileOutputStream);
                }
            }else{
                return VideoJSONResult.errorMsg("上传出错");
            }
        }catch (Exception e){
            e.printStackTrace();
            return VideoJSONResult.errorMsg("上传出错");
        }finally {
            if(fileOutputStream != null){
                try{
                    fileOutputStream.flush();
                    fileOutputStream.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if(inputStream != null){
                try{
                    inputStream.close();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return VideoJSONResult.ok(UploadDBPath);
    }

    @PostMapping("/addBgm")
    @ResponseBody
    public VideoJSONResult addBgm(Bgm bgm){
        videoService.addBgm(bgm);
        return VideoJSONResult.ok();
    }

    /**
     * 查询所有视频，供管理员禁止播放操作
     * @param page
     * @return
     */
    @PostMapping("/reportList")
    @ResponseBody
    public PagedResult reportList( Integer page){
        PagedResult result = videoService.queryAllVideos(page, 10);
        return result;
    }

    /**
     * 禁止播放视频
     * @param videoId
     * @return
     */
    @PostMapping("/forbidVideo")
    @ResponseBody
    public VideoJSONResult forbidVideo( String videoId){
        videoService.forbitVideo(videoId, VideoStatusEnum.Failure.value);
        return VideoJSONResult.ok();
    }

    /**
     * 恢复播放视频
     * @param videoId
     * @return
     */
    @PostMapping("/recoverVideo")
    @ResponseBody
    public VideoJSONResult recoverVideo( String videoId){
        videoService.forbitVideo(videoId, VideoStatusEnum.Success.value);
        return VideoJSONResult.ok();
    }

}
