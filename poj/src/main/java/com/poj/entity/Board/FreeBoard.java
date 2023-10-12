package com.poj.entity.Board;

import com.poj.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FreeBoard extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "free_board_id")
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String context;//글의 내용

    private int viewCount;

    @NotBlank
    private String author;//author

    @Builder
    public FreeBoard(String title, String context, String author){
        this.title = title;
        this.context = context;
        this.author = author;
        this.viewCount = 0;
    }

    public void upViewCount(){
        viewCount++;
    }
}
