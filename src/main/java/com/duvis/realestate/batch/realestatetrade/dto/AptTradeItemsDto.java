package com.duvis.realestate.batch.realestatetrade.dto;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="body")
public class AptTradeItemsDto {

    private List<AptTradeDto> items;

    @XmlElementWrapper(name="items")
    @XmlElement(name="item")
    public List<AptTradeDto> getItems() {
        return items;
    }

    public void setItems(List<AptTradeDto> items) {
        this.items = items;
    }
}
