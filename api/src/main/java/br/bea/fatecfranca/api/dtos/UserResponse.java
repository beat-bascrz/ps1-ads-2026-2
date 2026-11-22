package br.bea.fatecfranca.api.dtos;

import br.bea.fatecfranca.api.entities.User;


public record UserResponse(
   Long id,
   String fullname,
   String username,
   String email,
   Boolean isAdmin
) {


   public UserResponse(User user) {
       this(
           user.getId(),
           user.getFullname(),
           user.getUsername(),
           user.getEmail(),
           user.getIsAdmin()
       );
   }
}
