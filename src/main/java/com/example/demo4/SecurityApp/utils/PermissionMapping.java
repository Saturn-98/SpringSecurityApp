package com.example.demo4.SecurityApp.utils;

import com.example.demo4.SecurityApp.enums.Permission;
import com.example.demo4.SecurityApp.enums.Role;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.demo4.SecurityApp.enums.Permission.*;
import static com.example.demo4.SecurityApp.enums.Role.*;

public class PermissionMapping {
   private static final Map<Role, Set<Permission>> map = Map.of(
            USER , Set.of(POST_VIEW, USER_VIEW),
            CREATOR, Set.of(POST_CREATE, POST_VIEW, POST_UPDATE, USER_VIEW),
            ADMIN, Set.of(USER_CREATE,USER_DELETE, POST_DELETE, POST_VIEW, USER_VIEW)
    );

   public static Set<SimpleGrantedAuthority> getAuthoritiesFromRole(Role role){
       return map.get(role).stream()
               .map(permission -> new SimpleGrantedAuthority(permission.name()))
               .collect(Collectors.toSet());
   }
}
