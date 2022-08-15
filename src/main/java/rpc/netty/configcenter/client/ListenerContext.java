package rpc.netty.configcenter.client;

import rpc.netty.configcenter.event.MessageEvent;
import rpc.netty.configcenter.listener.MessageChangedListener;
import rpc.netty.configcenter.model.ConfigItem;

/**
 * @Author：zeqi
 * @Date: Created in 20:58 31/1/18.
 * @Description:
 */
public class ListenerContext {

    private MessageChangedListener listener;

    public ListenerContext() {
    }

    public void addListener(MessageChangedListener listener) {
        this.listener = listener;
    }


    public void fireMessageChaned(ConfigItem item) {

        MessageEvent event = new MessageEvent(item.toString(),"1","0");

        listener.messageChanged(event);
    }
}
