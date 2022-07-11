package com.csn.csn.comm.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sun.security.provider.Sun;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NaverApiNews {

    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    private List<items> items;

    @JsonIgnoreProperties(ignoreUnknown=true)
    static class items {
        private String title;
        private String originallink;
        private String link;
        private String description;
        private String pubDate;
    }//end class()

}//end class()
