package com.web.galera.taxapp.repository;

import com.web.galera.taxapp.datasource.random.RandomIntDataSource;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RandomRepositoryTest {

    @Test
    void testRepositoryReturnsExpectedList() {
        AtomicInteger counter = new AtomicInteger(0);
        RandomIntDataSource predictableSource = new RandomIntDataSource() {
            @Override
            public Integer get() {
                return counter.incrementAndGet();
            }
        };

        // 2. Создаём репозиторий
        RandomRepository<Integer> repository = new RandomRepository<>(predictableSource);

        // 3. Ожидаемый результат
        int count = 5;
        List<Integer> expected = IntStream.rangeClosed(1, count).boxed().toList();

        // 4. Вызываем метод
        List<Integer> actual = repository.getList(count);

        // 5. Проверяем
        assertEquals(expected, actual, "Test that two list has same elements");
        assertEquals(count, actual.size(), "Test that list size equals count");
    }

}
