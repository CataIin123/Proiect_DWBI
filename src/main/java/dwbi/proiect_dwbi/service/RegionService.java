package dwbi.proiect_dwbi.service;

import dwbi.proiect_dwbi.exception.ResourceAlreadyExistsException;
import dwbi.proiect_dwbi.exception.ResourceNotFoundException;
import dwbi.proiect_dwbi.model.District;
import dwbi.proiect_dwbi.model.Region;
import dwbi.proiect_dwbi.model.Ride;
import dwbi.proiect_dwbi.repository.RegionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RegionService {

    private final RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public Region findByRegionId(int regionId) {
        return regionRepository.findByRegionId(regionId);
    }

    public Page<Region> findPage(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 5);
        return regionRepository.findAll(pageable);
    }

    public Page<Region> findAllWithSort(String field, String direction, int pageNumber) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(field).ascending() : Sort.by(field).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, 5, sort);
        return regionRepository.findAll(pageable);
    }

    public void save(Region region) {
        Region storedRegion = regionRepository.findByRegionName(region.getRegionName());
        if(storedRegion != null){
        throw new ResourceAlreadyExistsException("Region " + region.getRegionName() + " already exists");
        }
        regionRepository.save(region);
    }

    @Transactional
    public Region update(Region region, int regionId) {
        Region storedRegion = regionRepository.findByRegionId(regionId);
        if(storedRegion == null){
            throw new ResourceNotFoundException("Region " + regionId + " not found");
        }
        Region storedRegionForName = regionRepository.findByRegionName(region.getRegionName());
        if(storedRegionForName != null && storedRegionForName.getRegionId() != regionId){
            throw new ResourceAlreadyExistsException("Region " + region.getRegionName() + " already exists");
        }
        return regionRepository.save(region);
    }


    @Transactional
    public void delete(int id) {
        regionRepository.deleteById(id);
    }
}
