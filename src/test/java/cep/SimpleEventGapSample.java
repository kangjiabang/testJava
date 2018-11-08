/*
 * Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package cep;
import org.wso2.siddhi.core.SiddhiAppRuntime;
import org.wso2.siddhi.core.SiddhiManager;
import org.wso2.siddhi.core.event.Event;
import org.wso2.siddhi.core.query.output.callback.QueryCallback;
import org.wso2.siddhi.core.stream.input.InputHandler;
import org.wso2.siddhi.core.util.EventPrinter;
//import org.wso2.siddhi.core.util.EventPrinter;

/**
 *
 * SimpleFilterSample.java Create on 2017年6月19日 下午10:54:41
 *
 * 类功能说明:   siddhi官方例子，数据过滤
 *
 * Copyright: Copyright(c) 2013
 * Company: COSHAHO
 * @Version 1.0
 * @Author coshaho
 */
public class SimpleEventGapSample
{
    public static void main(String[] args) throws InterruptedException
    {
        // Creating Siddhi Manager
        SiddhiManager siddhiManager = new SiddhiManager();

        String siddhiApp = "" +
                "define stream cseEventStream (symbol string, price float, volume long); " +
                "" +
                "@info(name = 'query1') " +
                "from every (e1=cseEventStream[convert(e1.price,'float') < 70.0]) -> not cseEventStream[e1.symbol == symbol] for 4 sec " +
                "select e1.symbol,e1.price " +
                "insert into outputStream ;";

        // Generating runtime
        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(siddhiApp);

        // Adding callback to retrieve output events from query
        siddhiAppRuntime.addCallback("query1", new QueryCallback()
        {
            @Override
            public void receive(long timeStamp, Event[] inEvents, Event[] removeEvents)
            {
                 EventPrinter.print(timeStamp, inEvents, removeEvents);
                //System.out.print(inEvents[0].getData(0) + " ");
            }
        });

        // Retrieving InputHandler to push events into Siddhi
        InputHandler inputHandler = siddhiAppRuntime.getInputHandler("cseEventStream");

        // Starting event processing
        siddhiAppRuntime.start();

        // Sending events to Siddhi
        inputHandler.send(new Object[]{"Welcome", 50.6f, 100L});
        inputHandler.send(new Object[]{"Welcome", 60.5f, 200L});
        /*inputHandler.send(new Object[]{"WSO2", 60.5f, 200L});
        inputHandler.send(new Object[]{"IBM", 50f, 30L});
        Thread.sleep(2000);
        inputHandler.send(new Object[]{"IBM", 76.6f, 400L});
        inputHandler.send(new Object[]{"siddhi!", 45.6f, 50L});
        inputHandler.send(new Object[]{"IBM", 80.6f, 200L});
        inputHandler.send(new Object[]{"hi!", 45.6f, 150L});*/
        Thread.sleep(50000);

        // Shutting down the runtime
        siddhiAppRuntime.shutdown();

        // Shutting down Siddhi
        siddhiManager.shutdown();
    }
}