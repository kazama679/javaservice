package com.ra.ss7.service;

import com.ra.ss7.model.entity.Worker;
import com.ra.ss7.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {

    @Autowired
    private WorkerRepository workerRepository;

    public List<Worker> getAllWorkers() {
        return workerRepository.findAll();
    }

    public Worker getWorkerById(Long id) {
        return workerRepository.findById(id).orElse(null);
    }

    public Worker addWorker(Worker worker) {
        return workerRepository.save(worker);
    }

    public Worker updateWorker(Long id, Worker worker) {
        Optional<Worker> optional = workerRepository.findById(id);
        if (optional.isPresent()) {
            Worker existing = optional.get();
            existing.setFullname(worker.getFullname());
            existing.setPhone(worker.getPhone());
            existing.setAddress(worker.getAddress());
            existing.setSalary(worker.getSalary());
            return workerRepository.save(existing);
        }
        return null;
    }

    public void deleteWorker(Long id) {
        workerRepository.deleteById(id);
    }
}
