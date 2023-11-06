package com.okoho.okoho.service;

import com.okoho.okoho.domain.UserAccount;
import com.okoho.okoho.service.dto.RegisterRequest;
import com.okoho.okoho.service.dto.UserAccountDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.okoho.okoho.domain.UserAccount}.
 */
public interface UserAccountService {
    /**
     * Save a userAccount.
     *
     * @param userAccountDTO the entity to save.
     * @return the persisted entity.
     */
    UserAccountDTO save(RegisterRequest userAccountDTO);
    UserAccountDTO register(RegisterRequest registerRequest);
    /**
     * Partially updates a userAccount.
     *
     * @param userAccountDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<UserAccountDTO> partialUpdate(UserAccountDTO userAccountDTO);

    /**
     * Get all the userAccounts.
     *
     * @return the list of entities.
     */
    List<UserAccountDTO> findAll();

    /**
     * Get the "id" userAccount.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UserAccountDTO> findOne(String id);

    /**
     * Delete the "id" userAccount.
     *
     * @param id the id of the entity.
     */
    void delete(String id);

    Object resetpassword(String email);

    boolean isEnabledUser(String username);
    public Optional<UserAccount> requestPasswordReset(String mail);
    public Optional<UserAccount> completePasswordReset(String newPassword, String key);
    public void changePassword(String currentClearTextPassword, String newPassword);
    public Optional<UserAccount> activateRegistration(String key);
    public Optional<UserAccount> getUserWithAuthorities() ;
}
