package dwbi.proiect_dwbi.service;

import dwbi.proiect_dwbi.exception.ResourceAlreadyExistsException;
import dwbi.proiect_dwbi.exception.ResourceNotFoundException;
import dwbi.proiect_dwbi.model.District;
import dwbi.proiect_dwbi.model.Town;
import dwbi.proiect_dwbi.repository.TownRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TownService {
    private final TownRepository townRepository;
    private final DistrictService districtService;

    public TownService(TownRepository townRepository, DistrictService districtService) {
        this.townRepository = townRepository;
        this.districtService = districtService;
    }

    public Town findByTownId(int departure) {
        return townRepository.findByTownId(departure);
    }

    public Page<Town> findPage(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 5);
        return townRepository.findAll(pageable);
    }

    public Page<Town> findAllWithSort(String field, String direction, int pageNumber) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(field).ascending() : Sort.by(field).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, 5, sort);
        return townRepository.findAll(pageable);
    }

    public void save(Town town, int districtId) {
        Town storedTown = townRepository.findByTownName(town.getTownName());
        if (storedTown != null) {
            throw new ResourceAlreadyExistsException("Town " + town.getTownName() + " already exists");
        }
        District storedDistrict = districtService.findByDistrictId(districtId);
        if (storedDistrict != null) {
            town.setDistrict(storedDistrict);
            townRepository.save(town);
        } else {
            throw new ResourceNotFoundException("District " + districtId + " not found");
        }
    }

    @Transactional
    public Town update(Town town, int townId, int districtId) {
        Town storedTown = townRepository.findByTownId(townId);
        if (storedTown == null) {
            throw new ResourceNotFoundException("Town " + townId + " not found");
        }
        Town storedTownForName = townRepository.findByTownName(town.getTownName());
        if (storedTownForName != null && storedTownForName.getTownId() != townId) {
            throw new ResourceAlreadyExistsException("Town " + town.getTownName() + " already exists");
        }
        District storedDistrict = districtService.findByDistrictId(districtId);
        if (storedDistrict != null) {
            storedTown.setDistrict(storedDistrict);
            storedTown.setTownName(town.getTownName());
            return townRepository.save(storedTown);
        } else {
            throw new ResourceNotFoundException("District " + districtId + " not found");
        }
    }

    @Transactional
    public void delete(int id) {
        townRepository.deleteById(id);
    }
}
