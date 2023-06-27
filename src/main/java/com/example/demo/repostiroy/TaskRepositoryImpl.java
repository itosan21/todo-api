package com.example.demo.repostiroy;

import com.example.demo.entity.Task;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TaskRepositoryImpl implements TaskRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Task findById(Long id) {
        return entityManager.find(Task.class, id);
    }

    @Override
    public List<Task> findAll() {
        return entityManager.createQuery("SELECT t FROM Task t", Task.class).getResultList();
    }

    @Override
    public Task save(Task task) {
        entityManager.persist(task);
        return task;
    }

    @Override
    public Task update(Task task) {
        return entityManager.merge(task);
    }

    @Override
    public void deleteById(Long id) {
        Task task = findById(id);
        if (task != null) {
            entityManager.remove(task);
        }
    }
}
