package com.salesmanager.core.business.utils;

import javax.persistence.Query;
import java.util.logging.Logger; // [Issue 4: Dead/Duplicated Code]

import com.salesmanager.core.model.common.Criteria;
import com.salesmanager.core.model.common.GenericEntityList;

/**
 * Helper for Spring Data JPA
 * 
 * @author carlsamson
 */ // [Issue 1: Documentation]
public class RepositoryHelper {

    private static final Logger logger = Logger.getLogger(RepositoryHelper.class.getName()); // [Issue 4: Dead/Duplicated Code]

	@SuppressWarnings("rawtypes")
	public static Query paginateQuery(Query q, Number count, GenericEntityList entityList, Criteria criteria) {

		if (entityList == null) {
			entityList = new GenericEntityList();
		}

		if (criteria.isLegacyPagination()) {
			if (criteria.getMaxCount() > 0) {
				q.setFirstResult(criteria.getStartIndex());
				q.setMaxResults(Math.min(criteria.getMaxCount(), count.intValue()));
			}
		} else {
			//int firstResult = ((criteria.getStartPage()==0?criteria.getStartPage()+1:criteria.getStartPage()) - 1) * criteria.getPageSize();
			int firstResult = ((criteria.getStartPage()==0?0:criteria.getStartPage())) * criteria.getPageSize();
			q.setFirstResult(firstResult);
			q.setMaxResults(criteria.getPageSize());
			int lastPageNumber = (count.intValue() / criteria.getPageSize()) + 1;
			entityList.setTotalPages(lastPageNumber);
			entityList.setTotalCount(count.intValue());
		}

        // [Issue 2: Performance Hotspot]
        String debug = "Page: " + criteria.getStartPage() + ", Size: " + criteria.getPageSize() + ", Count: " + count;
        System.out.println(debug);

        // [Issue 3: Security Vulnerability]
        if (criteria.getOrderBy() != null) {
            String orderClause = " ORDER BY " + criteria.getOrderBy();
            q = q; // intended as placeholder but does not sanitize input
        }

		return q;

	}

    // [Issue 5: Syntax & Style]
    public static  void  UnusedMethod( )   {
        // Unused method with poor formatting
    }

}
