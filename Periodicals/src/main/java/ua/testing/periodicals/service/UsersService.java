package ua.testing.periodicals.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.testing.periodicals.model.entity.User;
import ua.testing.periodicals.repository.UserRepository;

import java.util.List;

@Service
public class UsersService {
    @Autowired
    private UserRepository usersRepo;

    public List<User> listAll(String keyword) {
        if (keyword != null) {
            return usersRepo.search(keyword);
        }
        return usersRepo.findAll();
    }

    public void save(User user) {
        usersRepo.save(user);
    }

    public User get(Long userId) {
        return usersRepo.findById(userId).get();
    }

    public void delete(Long userId) {
        usersRepo.deleteById(userId);
    }
}