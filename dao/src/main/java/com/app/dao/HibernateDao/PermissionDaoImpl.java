package com.app.dao.HibernateDao;

import com.app.dao.PermissionDao;
import com.app.model.Permission;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by andrei on 15.09.16.
 */
@Transactional
@Repository
public class PermissionDaoImpl implements PermissionDao {

    @Value("from Permission where permissionname = :permissionname")
    private String getPermissionByPerName;

    @Value("from Permission")
    private String getAllPermissions;

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void addPermission(Permission permission) {
        try {
            Permission permissionCheck = getPermission(permission.getPermissionName());

        } catch (Exception e) {
            getSession().save(permission);
        }
    }

    @Override
    public Permission getPermission(int id) {
        return getSession().load(Permission.class, id);
    }

    @Override
    public Permission getPermission(String permissionName) throws Exception {
        Query query = getSession().createQuery(getPermissionByPerName);
        query.setParameter("permissionname", permissionName);
        if(query.list().size()==0){
            throw new Exception();
        } else {
            List<Permission> permissions = query.list();
            return permissions.get(0);
        }
    }

    @Override
    public void updatePermission(Permission permission) {
        Permission permissionEdit = getPermission(permission.getId());
        permission.setPermissionName(permission.getPermissionName());
        getSession().update(permissionEdit);
    }

    @Override
    public void deletePermission(int id) {
        Permission permission = getPermission(id);
        getSession().delete(permission);
    }

    @Override
    public List<Permission> getPermissions() {
        List<Permission> permissions = getSession().createQuery(getAllPermissions).list();
        return permissions;
    }
}