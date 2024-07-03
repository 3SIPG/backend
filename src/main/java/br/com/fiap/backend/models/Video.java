package br.com.fiap.backend.models;

import br.com.fiap.backend.dto.VideoData;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Table(name = "tb_videos")
@Entity(name = "Video")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String url;

    String description;

    private String banner;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "training_id")
    @JsonIgnore
    private Onboard onboard;

    public Video(VideoData data) {
        this.title = data.title();
        this.url = data.url();
        this.banner = data.banner();
        this.description = data.description();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Video video = (Video) o;
        return Objects.equals(id, video.id) && Objects.equals(title, video.title) && Objects.equals(url, video.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, url);
    }
}
