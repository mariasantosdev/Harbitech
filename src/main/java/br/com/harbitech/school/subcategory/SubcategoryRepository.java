package br.com.harbitech.school.subcategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory,Long> {
    Optional<Subcategory> findByCodeUrl(String codeUrl);

}
