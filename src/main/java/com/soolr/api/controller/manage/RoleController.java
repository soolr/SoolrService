package com.soolr.api.controller.manage;

import com.soolr.api.base.ApiBaseController;
import com.soolr.api.common.utils.PageUtil;
import com.soolr.api.common.utils.ResultUtil;
import com.soolr.api.common.vo.PageVo;
import com.soolr.api.common.vo.Result;
import com.soolr.api.entity.Permission;
import com.soolr.api.entity.Role;
import com.soolr.api.entity.RolePermission;
import com.soolr.api.entity.UserRole;
import com.soolr.api.service.RolePermissionService;
import com.soolr.api.service.RoleService;
import com.soolr.api.service.UserRoleService;
import com.soolr.api.service.mybatis.IPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


/**
 * @author Soolr
 */
@Slf4j
@RestController
@Api(description = "角色管理接口")
@RequestMapping("/api/role")
@Transactional
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private IPermissionService iPermissionService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping(value = "/getAllList",method = RequestMethod.GET)
    @ApiOperation(value = "获取全部角色")
    public Result<Object> roleGetAll(){

        List<Role> list = roleService.getAll();
        return new ResultUtil<Object>().setData(list);
    }

    @RequestMapping(value = "/getAllByPage",method = RequestMethod.GET)
    @ApiOperation(value = "分页获取角色")
    public Result<Page<Role>> getRoleByPage(@ModelAttribute PageVo page){

        Page<Role> list = roleService.findAll(PageUtil.initPage(page));
        for(Role role : list.getContent()){
            List<Permission> permissions = iPermissionService.findByRoleId(role.getId());
            role.setPermissions(permissions);
        }
        return new ResultUtil<Page<Role>>().setData(list);
    }

    @RequestMapping(value = "/setDefault",method = RequestMethod.POST)
    @ApiOperation(value = "设置或取消默认角色")
    public Result<Object> setDefault(@RequestParam String id,
                                     @RequestParam Boolean isDefault){

        Role role = roleService.get(id);
        if(role==null){
            return new ResultUtil<Object>().setErrorMsg("角色不存在");
        }
        role.setDefaultRole(isDefault);
        roleService.update(role);
        return new ResultUtil<Object>().setSuccessMsg("设置成功");
    }

    @RequestMapping(value = "/editRolePerm/{roleId}",method = RequestMethod.POST)
    @ApiOperation(value = "编辑角色分配权限")
    public Result<Object> editRolePerm(@PathVariable String roleId,
                                       @RequestParam(required = false) String[] permIds){

        //删除其关联权限
        rolePermissionService.deleteByRoleId(roleId);
        //分配新权限
        for(String permId : permIds){
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permId);
            rolePermissionService.save(rolePermission);
        }
        //手动批量删除缓存
        Set<String> keysUser = redisTemplate.keys("user:" + "*");
        redisTemplate.delete(keysUser);
        Set<String> keysUserRole = redisTemplate.keys("userRole:" + "*");
        redisTemplate.delete(keysUserRole);
        Set<String> keysUserPerm = redisTemplate.keys("userPermission:" + "*");
        redisTemplate.delete(keysUserPerm);
        Set<String> keysUserMenu = redisTemplate.keys("permission::userMenuList:*");
        redisTemplate.delete(keysUserMenu);
        return new ResultUtil<Object>().setData(null);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ApiOperation(value = "保存数据")
    public Result<Role> save(@ModelAttribute Role role){

        Role r = roleService.save(role);
        return new ResultUtil<Role>().setData(r);
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ApiOperation(value = "更新数据")
    public Result<Role> edit(@ModelAttribute Role entity){

        Role r = roleService.update(entity);
        //手动批量删除缓存
        Set<String> keysUser = redisTemplate.keys("user:" + "*");
        redisTemplate.delete(keysUser);
        Set<String> keysUserRole = redisTemplate.keys("userRole:" + "*");
        redisTemplate.delete(keysUserRole);
        return new ResultUtil<Role>().setData(r);
    }

    @RequestMapping(value = "/delAllByIds/{ids}",method = RequestMethod.DELETE)
    @ApiOperation(value = "批量通过ids删除")
    public Result<Object> delByIds(@PathVariable String[] ids){

        for(String id:ids){
            List<UserRole> list = userRoleService.findByRoleId(id);
            if(list!=null&&list.size()>0){
                return new ResultUtil<Object>().setErrorMsg("删除失败，包含正被用户使用关联的角色");
            }
        }
        for(String id:ids){
            roleService.delete(id);
            //删除关联权限
            rolePermissionService.deleteByRoleId(id);
        }
        return new ResultUtil<Object>().setSuccessMsg("批量通过id删除数据成功");
    }

}
