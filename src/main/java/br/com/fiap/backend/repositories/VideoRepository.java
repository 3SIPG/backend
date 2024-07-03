package br.com.fiap.backend.repositories;

import br.com.fiap.backend.models.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VideoRepository extends JpaRepository<Video, Long> {


    @Query(value = "SELECT * FROM tb_videos WHERE training_id = :trainingId AND id = :id", nativeQuery = true)
    Video findByOnboardAndVideoId(@Param("trainingId") Long trainingId, @Param("id") Long id);

}
