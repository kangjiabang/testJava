package protobuf.proto3;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static protobuf.proto3.SearchRequestProto.*;

public class ProtobufTest {

    public static void main(String[] args) throws IOException {




    }


    @Test
    public void buildObjectUseProtobuf() {
        SearchRequest searchRequest = SearchRequest.newBuilder().setPageNumber(1).setQuery("hallo").setResultPerPage(10).build();

        System.out.println(searchRequest);
    }

    @Test
    public void toByteArrayUseProtobuf() {
        SearchRequest searchRequest = SearchRequest.newBuilder().setPageNumber(1).setQuery("hallo").setResultPerPage(10).build();

        System.out.println(searchRequest.toByteArray());

    }

    @Test
    public void parseFromInputStreamUseProtobuf() throws IOException {
        SearchRequest searchRequest = SearchRequest.newBuilder().setPageNumber(1).setQuery("hallo").setResultPerPage(10).build();

       // System.out.println(searchRequest.toByteArray());

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(searchRequest.toByteArray());

        SearchRequest searchRequestFrom = SearchRequest.parseFrom(byteArrayInputStream);

        System.out.println(searchRequestFrom);
    }
}
