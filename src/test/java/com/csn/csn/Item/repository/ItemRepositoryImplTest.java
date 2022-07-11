package com.csn.csn.Item.repository;

import com.csn.csn.Item.entity.DictionaryItem;
import com.csn.csn.Item.entity.Item;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
@Transactional
@SpringBootTest
class ItemRepositoryImplTest {

    @Autowired
    private ItemRepositoryImpl itemRepositoryImpl;

    @BeforeEach
    void beforeEach() {
        List<Item> all = itemRepositoryImpl.findAll();

        for (Item item : all) {
            itemRepositoryImpl.delete(item);
        }
    }

    @Test
    @Rollback(value = false)
    @DisplayName("중복 저장")
    void duplicationSave() {
        // given
        DictionaryItem dictionaryItem1 = new DictionaryItem("타이틀1", "링크1", "설명1", "섬네일1");
        DictionaryItem dictionaryItem2 = new DictionaryItem("타이틀1", "링크1", "설명1", "섬네일1");

        // when
        itemRepositoryImpl.save(dictionaryItem1);

        try {
            itemRepositoryImpl.save(dictionaryItem2);
        } catch (Exception e) {
//            log.error("중복 저장 에러", e);
        }

        // then
//        assertThatThrownBy(() -> itemRepositoryImpl.save(dictionaryItem1))
//                .isInstanceOf(DataIntegrityViolationException.class);
    }


    @Test
    @DisplayName("특정 검색 결과 조회")
    void find() {
        // when
        DictionaryItem dictionaryItem = new DictionaryItem("타이틀1", "링크1", "설명1", "섬네일1");

        // given
        Long savedItemId = itemRepositoryImpl.save(dictionaryItem);

        // then
        Item findItem = itemRepositoryImpl.find(savedItemId).get();
        assertThat(findItem.getClass()).isEqualTo(DictionaryItem.class);
        assertThat(dictionaryItem.getId()).isEqualTo(findItem.getId());
    }

    @Test
    @DisplayName("모든 검색 결과 조회")
    void findAll() {
        // given
        DictionaryItem dictionaryItem1 = new DictionaryItem("타이틀1", "링크1", "설명1", "섬네일1");
        DictionaryItem dictionaryItem2 = new DictionaryItem("타이틀2", "링크2", "설명2", "섬네일2");

        // when
        itemRepositoryImpl.save(dictionaryItem1);
        itemRepositoryImpl.save(dictionaryItem2);

        // then
        List<Item> dictionaries = itemRepositoryImpl.findAll();
        assertThat(2).isEqualTo(dictionaries.size());

        for (Item dictionary : dictionaries) {
            DictionaryItem d = (DictionaryItem) dictionary;
            log.info("{}", d);
        }
    }

    @Test
    @DisplayName("특정 타입 지정 조회")
    void findAllSpecificType() {
        // given
        DictionaryItem dictionaryItem1 = new DictionaryItem("타이틀1", "링크1", "설명1", "섬네일1");
        DictionaryItem dictionaryItem2 = new DictionaryItem("타이틀2", "링크2", "설명2", "섬네일2");

        // when
        itemRepositoryImpl.save(dictionaryItem1);
        itemRepositoryImpl.save(dictionaryItem2);

        // then
        List<Item> dictionaries = itemRepositoryImpl.findAllSpecificType("DictionaryItem");
        assertThat(2).isEqualTo(dictionaries.size());

        for (Item dictionary : dictionaries) {
            log.info("{}", (DictionaryItem) dictionary);
        }
    }

    @Test
    @DisplayName("삭제")
    void delete() {
        // given
        DictionaryItem dictionaryItem = new DictionaryItem("타이틀1", "링크1", "설명1", "섬네일1");

        // when
        itemRepositoryImpl.save(dictionaryItem);
        itemRepositoryImpl.delete(dictionaryItem);

        // then
        Optional<Item> result = itemRepositoryImpl.find(dictionaryItem.getId());
        assertThatThrownBy(() -> result.get())
                .isInstanceOf(NoSuchElementException.class);
    }
}