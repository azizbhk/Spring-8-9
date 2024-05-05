package com.aziz.voyages.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aziz.voyages.entities.Categorie;
public interface CategorieRepository extends JpaRepository<Categorie, Long>
{
}