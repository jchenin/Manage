package jin.chen.util;

import jin.chen.enums.BgmOperatorEnum;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZKCurator {
    //客户端
    private CuratorFramework client = null;
    final static Logger log = LoggerFactory.getLogger(ZKCurator.class);
    public ZKCurator(CuratorFramework client){
        this.client = client;
    }
    //初始化
    private void init(){
        client = client.usingNamespace("admin");
        try {
            //看看admin命名空间下是否有bgm节点
            if (client.checkExists().forPath("/bgm") == null) {
                client.create().creatingParentsIfNeeded()           //递归创建
                        .withMode(CreateMode.PERSISTENT)        //节点类型：持久节点
                        .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)  //acl: 匿名权，可游客访问
                        .forPath("/bgm");
                log.info("zookeeper初始化成功");
                log.info("zookeeper服务器状态： {}", client.isStarted());
            }
        }catch (Exception e){
            log.error("zookeeper初始化失败");
            e.printStackTrace();
        }
    }

    /**
     * 增加或者删除bgm,向zk服务写入子节点，供小程序监听
     * @param bgmId
     */
    public void operatorBgm(String bgmId, String operatorObj){
        try {
            System.out.println("写入" + operatorObj);
            client.create().creatingParentsIfNeeded()           //递归创建
                    .withMode(CreateMode.PERSISTENT)        //节点类型：持久节点
                    .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)  //acl: 匿名权，可游客访问
                    .forPath("/bgm/" + bgmId , operatorObj.getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
