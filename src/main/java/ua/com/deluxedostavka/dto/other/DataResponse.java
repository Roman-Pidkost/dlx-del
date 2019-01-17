package ua.com.deluxedostavka.dto.other;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DataResponse<T> {
    private List<T> content = new ArrayList<>();

    private Long totalElements;

    private Integer totalPages;

    public DataResponse() {
    }

    public DataResponse(List<T> content, Long totalElements) {
        this.content = content;
        this.totalElements = totalElements;
    }

    public DataResponse(List<T> content, Long totalElements,Integer totalPages) {
        this.content = content;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }
}
