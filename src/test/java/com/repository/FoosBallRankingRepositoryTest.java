package com.repository;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.FoosballRankingSystemApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FoosballRankingSystemApplication.class)
@ActiveProfiles("test")
public class FoosBallRankingRepositoryTest {

}
