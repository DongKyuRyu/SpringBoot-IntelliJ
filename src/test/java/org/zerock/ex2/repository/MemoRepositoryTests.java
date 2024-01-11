package org.zerock.ex2.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.ex2.Entity.Memo;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemoRepositoryTests {
    @Autowired
    MemoRepository memoRepository;

    @Test
    public void testClass() {
        System.out.println(memoRepository.getClass().getName());
    }

    @Test
    public void testInsertDummies() {

        IntStream.rangeClosed(1, 100).forEach(i -> {
           Memo memo = Memo.builder().memoText("Sample..." + i).build();
           memoRepository.save(memo);
        });
    }

    @Test
    public void testSelect() {
        Long mno = 100L;

        Optional<Memo> result = memoRepository.findById(mno);

        System.out.println("===========================================");

        if (result.isPresent()) {
            Memo memo = result.get();
            System.out.println(memo);
        }
    }

    @Transactional
    @Test
    public void testSelect2 () {
        Long mno = 101L;

        Memo memo = memoRepository.getOne(mno);

        System.out.println("=====================================");

        System.out.println(memo);
    }

    @Test
    public void testUpdate() {
        Memo memo = Memo.builder().mno(100L).memoText("Update Text").build();

        System.out.println(memoRepository.save(memo));
    }

    @Test
    public void testDelete() {
        // 여러개의 데이터를 삭제할때
        Long mno = 0L;
        for (Long i = 101L; i <= 200L; i++) {
            mno = i;
            memoRepository.deleteById(mno);
        }
    }

    @Test
    public void testDelete2() {
        Long mno = 100L;
        try {
            memoRepository.deleteById(mno);
        }catch (Exception e) {
            System.out.println("===========================================");
        }
    }
}
