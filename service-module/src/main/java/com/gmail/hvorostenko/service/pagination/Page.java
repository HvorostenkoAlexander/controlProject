package com.gmail.hvorostenko.service.pagination;

import com.gmail.hvorostenko.service.PageService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.gmail.hvorostenko.repository.constant.ConstRepository.MAX_RESULTS_CONST;


@Service
public class Page<T> implements PageService<T> {
    private final EntityManager entityManager;

    public Page(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Integer> countPage(T entity) {
        String queryString = "select count(*) from " + entity.getClass().getName() + " as e";
        Query query = entityManager.createQuery(queryString);
        int count = ((Number) query.getSingleResult()).intValue();
        List<Integer> pageNumbers = new ArrayList<>();
        if (count == 0) {
            return pageNumbers;
        }
        Integer totalPages;
        if ((count % MAX_RESULTS_CONST) != 0) {
            totalPages = count / MAX_RESULTS_CONST;
            totalPages++;
        } else {
            totalPages = count / MAX_RESULTS_CONST;
        }
        if (totalPages > 0) {
            pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            return pageNumbers;
        }
        return pageNumbers;
    }
}
