package netty.configcenter.client;

import io.netty.handler.codec.json.JsonObjectDecoder;
import netty.configcenter.event.MessageEvent;
import netty.configcenter.listener.MessageChangedListener;
import netty.configcenter.model.ConfigItem;

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
