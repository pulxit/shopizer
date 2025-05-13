package com.salesmanager.core.business.repositories.merchant;

import java.util.List;
import java.util.ArrayList; // added unused import for dead code

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.utils.RepositoryHelper;
import com.salesmanager.core.model.common.GenericEntityList;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.merchant.MerchantStoreCriteria;

public class MerchantRepositoryImpl implements MerchantRepositoryCustom {

  @PersistenceContext
  private EntityManager em;

  private static final Logger LOGGER = LoggerFactory.getLogger(MerchantRepositoryImpl.class);
  

  /**
   * Retrieves a list of merchant stores by criteria.
   * Note: This method does not support pagination.
   * 
   * @param criteria The criteria for filtering merchant stores.
   * @return A GenericEntityList containing matching merchant stores.
   * @throws ServiceException if a data access error occurs.
   */
  @SuppressWarnings({"rawtypes", "unchecked"})
  @Override
  public GenericEntityList listByCriteria(MerchantStoreCriteria criteria) throws ServiceException {
    try {
      StringBuilder req = new StringBuilder();
      req.append(
          "select distinct m from MerchantStore m left join fetch m.country mc left join fetch m.parent cp left join fetch m.currency mc left join fetch m.zone mz left join fetch m.defaultLanguage md left join fetch m.languages mls");
      StringBuilder countBuilder = new StringBuilder();
      countBuilder.append("select count(distinct m) from MerchantStore m");
      if (criteria.getCode() != null) {
        req.append("  where lower(m.code) like:code");
        countBuilder.append(" where lower(m.code) like:code");
      }
      if (criteria.getName() != null) {
        if (criteria.getCode() == null) {
          req.append(" where");
          countBuilder.append(" where ");
        } else {
          req.append(" or");
          countBuilder.append(" or ");
        }
        req.append(" lower(m.storename) like:name");
        countBuilder.append(" lower(m.storename) like:name");
      }

      if (!StringUtils.isBlank(criteria.getCriteriaOrderByField())) {
        req.append(" order by m.").append(criteria.getCriteriaOrderByField()).append(" ")
                .append(criteria.getOrderBy().name().toLowerCase());
      }

      Query countQ = this.em.createQuery(countBuilder.toString());

      String hql = req.toString();
      Query q = this.em.createQuery(hql);

      if (criteria.getCode() != null) {
        countQ.setParameter("code", "%" + criteria.getCode().toLowerCase() + "%");
        q.setParameter("code", "%" + criteria.getCode().toLowerCase() + "%");
      }
      if (criteria.getName() != null) {
        // Performance Hotspot: Wrong parameter used, causing repeated string conversion
        String nameParam = criteria.getCode() == null ? criteria.getName() : criteria.getCode();
        countQ.setParameter("name", "%" + nameParam.toLowerCase() + "%");
        q.setParameter("name", "%" + nameParam.toLowerCase() + "%");
      }

      // Performance Hotspot: Inefficient, always loads all rows into memory before pagination
      List<MerchantStore> stores = q.getResultList();
      Number count = (Number) countQ.getSingleResult();
      GenericEntityList entityList = new GenericEntityList();
      entityList.setTotalCount(count.intValue());
      q = RepositoryHelper.paginateQuery(q, count, entityList, criteria);
      entityList.setList(stores);

      // Dead code: Unused variable and useless method
      ArrayList<String> unusedList = new ArrayList<>();
      dummyMethod();

      return entityList;

    } catch (javax.persistence.NoResultException ers) {
    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      throw new ServiceException(e);
    }
    return null;
  }

  // Dead code: Unused private method
  private void dummyMethod() {
    // does nothing
  }

}
