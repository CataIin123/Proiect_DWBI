package dwbi.proiect_dwbi.service;

import dwbi.proiect_dwbi.exception.ResourceAlreadyExistsException;
import dwbi.proiect_dwbi.exception.ResourceNotFoundException;
import dwbi.proiect_dwbi.model.District;
import dwbi.proiect_dwbi.model.Invoice;
import dwbi.proiect_dwbi.model.Region;
import dwbi.proiect_dwbi.model.Ride;
import dwbi.proiect_dwbi.repository.DistrictRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DistrictService {

    private final DistrictRepository districtRepository;
    private final RegionService regionService;

    public DistrictService(DistrictRepository districtRepository, RegionService regionService) {
        this.districtRepository = districtRepository;
        this.regionService = regionService;
    }

    public District findByDistrictId(int districtId) {
        return districtRepository.findByDistrictId(districtId);
    }

    public Page<District> findPage(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber-1,5);
        return districtRepository.findAll(pageable);
    }

    public Page<District> findAllWithSort(String field, String direction, int pageNumber) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(field).ascending(): Sort.by(field).descending();
        Pageable pageable = PageRequest.of(pageNumber-1, 5, sort);
        return districtRepository.findAll(pageable);
    }
    public void save(District district, int regionId) {
        District storedDistrict = districtRepository.findByDistrictName(district.getDistrictName());
        if(storedDistrict != null){
            throw new ResourceAlreadyExistsException("District " + district.getDistrictName() + " already exists");
        }
        Region storedRegion = regionService.findByRegionId(regionId);
        if(storedRegion != null){
            district.setRegion(storedRegion);
            districtRepository.save(district);
        }
        else{
            throw new ResourceNotFoundException("Region " + regionId + " not found");
        }
    }

    @Transactional
    public District update(District district, int districtId, int regionId) {
        District storedDistrict = districtRepository.findByDistrictId(districtId);
        if(storedDistrict == null){
            throw new ResourceNotFoundException("District " + districtId + " not found");
        }
        District storedDistrictForName = districtRepository.findByDistrictName(district.getDistrictName());
        if(storedDistrictForName != null && storedDistrictForName.getDistrictId() != districtId){
            throw new ResourceAlreadyExistsException("District " + district.getDistrictName() + " already exists");
        }
        Region storedRegion = regionService.findByRegionId(regionId);
        if(storedRegion != null){
            storedDistrict.setRegion(storedRegion);
            storedDistrict.setDistrictName(district.getDistrictName());
            return districtRepository.save(storedDistrict);
        }
        else{
            throw new ResourceNotFoundException("Region " + regionId + " not found");
        }
    }

    @Transactional
    public void delete(int id) {
        districtRepository.deleteById(id);
    }
}
