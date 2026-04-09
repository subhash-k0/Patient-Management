package com.inventory.inventorysystem.services;

import com.inventory.inventorysystem.dtos.LoginRequest;
import com.inventory.inventorysystem.dtos.RegisterRequest;
import com.inventory.inventorysystem.dtos.Response;
import com.inventory.inventorysystem.dtos.UserDTO;
import com.inventory.inventorysystem.models.User;

public interface UserService {
    Response registerUser(RegisterRequest registerRequest);

    Response loginUser(LoginRequest loginRequest);

    Response getAllUsers();

    User getCurrentLoggedInUser();

    Response getUserById(Long id);

    Response updateUser(Long id, UserDTO userDTO);

    Response deleteUser(Long id);

    Response getUserTransactions(Long id);
}