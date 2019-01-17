package ua.com.deluxedostavka.dto.other;


import lombok.Getter;
import lombok.Setter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Setter
@Getter
public class PageResponse<G> implements Page {

    private List<G> content = new ArrayList<>();

    private Integer number;

    private Integer size;

    private Integer totalPages;

    private Long totalElements;

    private Boolean first;

    private Boolean last;

    private Sort sort;

    private Boolean hasNext;

    private Boolean hasPrevious;

    private Pageable nextPageable;

    private Pageable previousPageable;

    public PageResponse() {
    }

    public PageResponse(List<G> content, Long totalElements) {
        this.content = content;
        this.totalElements = totalElements;
    }

    @Override
    public int getTotalPages() {
        return totalPages;
    }

    @Override
    public long getTotalElements() {
        return totalElements;
    }

    @Override
    public Page map(Converter converter) {
        return null;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getNumberOfElements() {
        return 0;
    }

    @Override
    public List getContent() {
        return content;
    }

    @Override
    public boolean hasContent() {
        return content.size()>0;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public boolean isFirst() {
        return first;
    }

    @Override
    public boolean isLast() {
        return last;
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public boolean hasPrevious() {
        return hasPrevious;
    }

    @Override
    public Pageable nextPageable() {
        return nextPageable;
    }

    @Override
    public Pageable previousPageable() {
        return previousPageable;
    }

    @Override
    public Iterator iterator() {
        return null;
    }
}
