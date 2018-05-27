package service;

import model.Restouran;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repository.RestouranRepository;

import java.util.List;

import static util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestouranServiceImpl implements RestouranService {

    private final RestouranRepository restouranRepository;

    @Autowired
    public RestouranServiceImpl(RestouranRepository repository) {
        this.restouranRepository = repository;
    }

    @Override
    public Restouran get(int id, int userId) {
        return checkNotFoundWithId(restouranRepository.get(id, userId), id);
    }

    @Override
    public List<Restouran> getAll(int userId) {
        return restouranRepository.getAll(userId);
    }

    @Override
    public boolean delete(int id, int userId) {
        return (restouranRepository.delete(id, userId));
    }

    @Override
    public Restouran update(Restouran restouran, int restouranId) {
        return checkNotFoundWithId(restouranRepository.save(restouran, restouranId), restouran.getId());
    }
    @Override
    public Restouran create(Restouran restouran, int restouranId) {
        Assert.notNull(restouran, "Restouran must not be null");
        return restouranRepository.save(restouran, restouranId);
    }



}
