package lk.syscolabs.candidatemanager.service;

import lk.syscolabs.candidatemanager.model.Position;
import lk.syscolabs.candidatemanager.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {
    @Autowired
    private PositionRepository positionRepository;

    public List<Position> findAll() {
        return positionRepository.findAll();
    }

    public Position getOne(Integer id) {
        if (positionRepository.existsById(id)) return positionRepository.getOne(id);
        else return null;
    }

    public Position save(Position position) {
        return positionRepository.saveAndFlush(position);
    }

    public List<Position> findAllByName(String name) {
        return positionRepository.findAllByName(name);
    }

}