package com.ggsl.Helper;

import org.modelmapper.ModelMapper;
import com.ggsl.entities.PageableResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class helper{

    public static <U,V> PageableResponse<V> getPageableResponse(Page<U> page, Class<V> type){

        List<U> entity = page.getContent();
        List<V> userDtoList = entity.stream().map(object -> new ModelMapper().map(object,type)).collect(Collectors.toList());
        PageableResponse<V> pageableResponse = new PageableResponse<>();
        pageableResponse.setContent(userDtoList);
        pageableResponse.setPageNumber(page.getNumber()+1);
        pageableResponse.setPageSize(page.getSize());
        pageableResponse.setTotalPages(page.getTotalPages());
        pageableResponse.setTotalElements(page.getTotalElements());
        pageableResponse.setLastPage(page.isLast());

        return pageableResponse;
    }

}
