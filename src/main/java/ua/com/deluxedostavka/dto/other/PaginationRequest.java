package ua.com.deluxedostavka.dto.other;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Getter @Setter
public class PaginationRequest {

    private Integer page;

    private Integer size;

    private SortRequest sort;

    public PageRequest toPageRequest(){
        PageRequest pageRequest;
        if(sort == null){
            pageRequest = new PageRequest(page, size);
        }else{
            pageRequest = new PageRequest(page, size, new Sort(sort.getDirection(), sort.getFieldName()));
        }
        return pageRequest;
    }
}
