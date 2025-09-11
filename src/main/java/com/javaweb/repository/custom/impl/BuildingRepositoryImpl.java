package com.javaweb.repository.custom.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@Repository
//@Primary //Dung để sử dụng ưu tiên nếu có 2 repo implement 1 interface
public class BuildingRepositoryImpl implements BuildingRepositoryCustom{
    @PersistenceContext
    private EntityManager entityManager;


//    @Autowired
//    private BuildingRepository buildingRepo;

    public static void joinTable(BuildingSearchBuilder builldingSearchbuilder, StringBuilder sql) {
//        List<String> typeCode = builldingSearchbuilder.getTypeCode();
//        if (typeCode  != null && typeCode.size() != 0) {
//            sql.append("JOIN buildingrenttype r ON b.id = r.buildingid ");
//            sql.append(" left JOIN renttype t ON t.id = r.renttypeid ");
//        }

    }

    public static void queryNormal(BuildingSearchBuilder builldingSearchbuilder,StringBuilder where) {
        List<String> typeCode = builldingSearchbuilder.getTypeCode();
        if (typeCode  != null && typeCode.size() != 0) {
            where.append(" AND (");
            String sql = typeCode.stream().map(it -> "b.type like ('%" + it + "%')").collect(Collectors.joining(" AND "));
            where.append(sql);
            where.append(" ) ");
        }
        try {
            Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
            for (Field item : fields) {
                // Lấy ra field có tên "name" (nếu không tìm thấy, nó sẽ bắn NoSuchFieldException)
                item.setAccessible(true);
                String fieldName = item.getName();
                if(!fieldName.equals("staffid") && !fieldName.startsWith("Area")
                        && !fieldName.startsWith("rentPrice")) {
//        			String value = item.get(builldingSearchbuilder).toString();
                    Object value = item.get(builldingSearchbuilder);
                    if( value != null && value != "") { //phải thêm != "" vì String có ""
                        if (item.getType().getName().equals("java.lang.Long") || item.getType().getName().equals("java.lang.Integer") ||
                                item.getType().getName().equals("java.lang.Float")) {
                            where.append(" AND b." + fieldName + " = " + value);
                        }
                        else if (item.getType().getName().equals("java.lang.String") ) {
                            where.append(" AND b." + fieldName + " LIKE '%" + value + "%'");
                        }
                    }
                }
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static void querySpecial(BuildingSearchBuilder builldingSearchbuilder, StringBuilder where) {
        Long staffid = builldingSearchbuilder.getStaffid();
        if (staffid != null) {
            where.append(" AND EXISTS (Select * from assignmentbuilding a where  b.id = a.buildingid  "
                    + " and a.staffid = " + staffid);
            where.append(" ) ");
        }


        Long rentareaFrom =  builldingSearchbuilder.getRentAreaFrom();
        Long rentareaTo =  builldingSearchbuilder.getRentAreaTo();
        if (rentareaFrom != null || rentareaTo != null) {
            where.append(" and EXISTS (Select * from rentarea s where s.buildingid = b.id ");
            if (rentareaFrom != null ) {
                where.append(" and s.value >= " + rentareaFrom);
            }
            if (rentareaTo != null) {
                where.append(" and s.value <= " + rentareaTo);
            }
            where.append(")");
        }

        Long rentpriceFrom =  builldingSearchbuilder.getRentPriceFrom();
        Long rentpriceTo =  builldingSearchbuilder.getRentPriceTo();
        if (rentpriceFrom != null ) {
            where.append(" and rentprice >= " + rentpriceFrom);
        }
        if (rentpriceTo != null   ) {
            where.append(" and rentprice <= " + rentpriceTo);
        }
//        List<String> typeCode = builldingSearchbuilder.getTypeCode();
//        if (typeCode  != null && typeCode.size() != 0) {
//            where.append(" AND (");
//            String sql = typeCode.stream().map(it -> "t.code like ('%" + it + "%')").collect(Collectors.joining(" AND "));
//            where.append(sql);
//            where.append(" ) ");
//        }

    }
    @Override
    public List<BuildingEntity> findAll(BuildingSearchBuilder builldingSearchbuilder, Pageable pageable) {

        StringBuilder sql = new StringBuilder(buildQueryFilter());
        joinTable(builldingSearchbuilder, sql);

        StringBuilder where = new StringBuilder(" where 1 = 1 ");
        queryNormal(builldingSearchbuilder, where);
        querySpecial(builldingSearchbuilder, where);
        where.append(" GROUP BY b.id ");
        where.append(" LIMIT ").append(pageable.getPageSize()).append("\n")
                .append(" OFFSET ").append(pageable.getOffset());
        sql.append(where);

        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return query.getResultList();

    }

    @Override
    public int countTotalItem(BuildingSearchBuilder builldingSearchbuilder) {
        StringBuilder sql = new StringBuilder(buildQueryFilter());
        joinTable(builldingSearchbuilder, sql);

        StringBuilder where = new StringBuilder(" where 1 = 1 ");
        queryNormal(builldingSearchbuilder, where);
        querySpecial(builldingSearchbuilder, where);
        where.append(" GROUP BY b.id ");
        sql.append(where);

        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return query.getResultList().size();
    }

    private String buildQueryFilter() {
        String sql = "SELECT DISTINCT b.* FROM building b ";
        return sql;
    }
}
