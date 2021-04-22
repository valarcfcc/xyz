package com.valarcfcc.xyz;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.valarcfcc.xyz.utils.StringUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Scanner;

public class InterestTest {
    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new Exception("请输入正确的" + tip + "！");
    }

    private static final int INTEREST_RATE = 6;
    private static final int PENALTY_INTEREST_RATE = 12;
    @Test
    public void calculate(){
        // 本金
        BigDecimal principal = BigDecimal.ZERO;
        // 当期本金
        BigDecimal currentPrincipal = BigDecimal.ZERO;
        // 逾期本金
        BigDecimal overduePrincipal = BigDecimal.ZERO;
        // 当前利息
        BigDecimal currentInterest = BigDecimal.ZERO;
        // 逾期利息
        BigDecimal overdueInterest = BigDecimal.ZERO;
        // 当期罚息
        BigDecimal currentPenaltyInterest = BigDecimal.ZERO;
        // 逾期罚息
        BigDecimal overduePenaltyInterest = BigDecimal.ZERO;
        // 当期复利
        BigDecimal compoundInterest = BigDecimal.ZERO;
        // 逾期复利
        BigDecimal overdueCompoundInterest = BigDecimal.ZERO;
        // 当期罚息的复利
        BigDecimal compoundPenaltyInterest = BigDecimal.ZERO;
        // 逾期罚息的复利
        BigDecimal overdueCompoundPenaltyInterest = BigDecimal.ZERO;





        //
        //
        //
        //
        //
        //

    }


}
