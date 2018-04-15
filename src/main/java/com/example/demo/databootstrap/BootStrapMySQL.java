package com.example.demo.databootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.example.demo.model.Category;
import com.example.demo.model.UnitOfMeasurement;
import com.example.demo.respositories.CategoryRepository;
import com.example.demo.respositories.UnitOfMeasurementRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Profile({"dev", "prod"})
public class BootStrapMySQL implements ApplicationListener<ContextRefreshedEvent> {

	private final CategoryRepository categoryRepository;
	private final UnitOfMeasurementRepository unitOfMeasureRepository;

	public BootStrapMySQL(CategoryRepository categoryRepository,
			UnitOfMeasurementRepository unitOfMeasureRepository) {
		this.categoryRepository = categoryRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

		if (categoryRepository.count() == 0L){
			log.debug("Loading Categories");
			loadCategories();
		}

		if (unitOfMeasureRepository.count() == 0L){
			log.debug("Loading UOMs");
			loadUom();
		}
	}

	private void loadCategories(){
		Category cat1 = new Category();
		cat1.setDescription("American");
		categoryRepository.save(cat1);

		Category cat2 = new Category();
		cat2.setDescription("Italian");
		categoryRepository.save(cat2);

		Category cat3 = new Category();
		cat3.setDescription("Mexican");
		categoryRepository.save(cat3);

		Category cat4 = new Category();
		cat4.setDescription("Fast Food");
		categoryRepository.save(cat4);
	}

	private void loadUom(){
		UnitOfMeasurement uom1 = new UnitOfMeasurement();
		uom1.setDescription("Teaspoon");
		unitOfMeasureRepository.save(uom1);

		UnitOfMeasurement uom2 = new UnitOfMeasurement();
		uom2.setDescription("Tablespoon");
		unitOfMeasureRepository.save(uom2);

		UnitOfMeasurement uom3 = new UnitOfMeasurement();
		uom3.setDescription("Cup");
		unitOfMeasureRepository.save(uom3);

		UnitOfMeasurement uom4 = new UnitOfMeasurement();
		uom4.setDescription("Pinch");
		unitOfMeasureRepository.save(uom4);

		UnitOfMeasurement uom5 = new UnitOfMeasurement();
		uom5.setDescription("Ounce");
		unitOfMeasureRepository.save(uom5);

		UnitOfMeasurement uom6 = new UnitOfMeasurement();
		uom6.setDescription("Each");
		unitOfMeasureRepository.save(uom6);

		UnitOfMeasurement uom7 = new UnitOfMeasurement();
		uom7.setDescription("Pint");
		unitOfMeasureRepository.save(uom7);

		UnitOfMeasurement uom8 = new UnitOfMeasurement();
		uom8.setDescription("Dash");
		unitOfMeasureRepository.save(uom8);
	}

}
