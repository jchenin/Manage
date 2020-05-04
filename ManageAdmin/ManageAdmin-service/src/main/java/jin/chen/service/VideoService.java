package jin.chen.service;

import jin.chen.pojo.Bgm;
import jin.chen.utils.PagedResult;

public interface VideoService {

    /**
     * 添加背景音乐
     * @param bgm
     */
    public void addBgm(Bgm bgm);

    /**
     * 背景音乐列表展示
     * @param page 第几页
     * @param pageSize  当前页数要展示的数量
     * @return
     */
    public PagedResult queryBgmList(Integer page, Integer pageSize);

    /**
     * 删除背景音乐
     * @param bgmId
     */
    public void deleteBgm(String bgmId);

    /**
     * 查询所有视频，分页
     * @param page
     * @param pageSize
     * @return
     */
    public PagedResult queryAllVideos(Integer page, Integer pageSize);

    /**
     * 禁止播放视频
     * @param videoId
     * @param status
     */
    public void forbitVideo(String videoId, Integer status);
}
