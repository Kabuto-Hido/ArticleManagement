package com.example.demo.service.impl;

import com.example.demo.dto.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class FiltersSpecificationServiceImpl<T> {
    Map<String, String> mapEntity = new HashMap<String, String>() {{
        put("user", "userId");
        put("category", "categoryId");
        put("post", "postId");
        put("order", "orderId");
        put("role", "userRole");
    }};
    public Specification<T> getSearchSpecification(SearchCriteria searchCriteria) {
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                //String strToSearch = searchCriteria.getValue().toString().toLowerCase();
                return criteriaBuilder.equal(root.get(searchCriteria.getFilterKey()), searchCriteria.getValue());
            }
        };
    }

    public Specification<T> getSearchSpecification(List<SearchCriteria> searchCriterias) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            for (SearchCriteria s : searchCriterias) {
                switch (s.getOperation()) {
                    case LIKE:
                        Predicate fullLike = criteriaBuilder.like(root.get(s.getFilterKey()), "%" + s.getValue() + "%");
                        predicates.add(fullLike);
                        break;
                    case EQUAL:
                        Predicate equal = criteriaBuilder.equal(root.get(s.getFilterKey()), s.getValue());
                        predicates.add(equal);
                        break;
                    case IN:
                        String[] split = s.getValue().split(",");
                        Predicate in = criteriaBuilder.in(root.get(s.getFilterKey()).in(Arrays.asList(split)));
                        predicates.add(in);
                        break;
                    case GREATER_THAN:
                        Predicate greaterThan;
                        if (s.getValue().contains("-")) {
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                            try {
                                Date value = formatter.parse(s.getValue());
                                greaterThan = criteriaBuilder.greaterThan(root.get(s.getFilterKey()).as(Date.class), value);
                            } catch (ParseException e) {
                                throw new RuntimeException(e);
                            }
                        } else {
                            greaterThan = criteriaBuilder.greaterThan(root.get(s.getFilterKey()), s.getValue());
                        }
                        predicates.add(greaterThan);
                        break;
                    case LESS_THAN:
                        Predicate lessThan;
                        if (s.getValue().contains("-")) {
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                            try {
                                Date value = formatter.parse(s.getValue());
                                lessThan = criteriaBuilder.lessThan(root.get(s.getFilterKey()).as(Date.class), value);
                            } catch (ParseException e) {
                                throw new RuntimeException(e);
                            }
                        } else {
                            lessThan = criteriaBuilder.greaterThan(root.get(s.getFilterKey()), s.getValue());
                        }
                        predicates.add(lessThan);
                        break;
                    case BETWEEN:
                        String[] splitDate = s.getValue().split(",");
                        Predicate between;
                        if (splitDate[0].contains("-")) {
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                            try {
                                Date from = formatter.parse(splitDate[0]);
                                Date to = formatter.parse(splitDate[1]);
                                between = criteriaBuilder.between(root.get(s.getFilterKey()).as(Date.class), from, to);
                            } catch (ParseException e) {
                                throw new RuntimeException(e);
                            }
                        } else {
                            between = criteriaBuilder.between(root.get(s.getFilterKey()), Long.parseLong(splitDate[0]), Long.parseLong(splitDate[1]));
                        }
                        predicates.add(between);
                        break;
                    case JOIN:
                        Predicate join = criteriaBuilder.equal(root.join(mapEntity.get(s.getJoinTable())).get(s.getFilterKey()), s.getValue());
                        predicates.add(join);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected operation value");
                }


            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

    }
}
