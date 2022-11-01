package lotto.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;

public class InputView {

    private final static Scanner SCANNER = new Scanner(System.in);

    private InputView() {}

    public static long readPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        long amount = Long.parseLong(SCANNER.nextLine());

        if (amount < 1000) {
            throw new IllegalArgumentException("1000원 이상의 구입금액을 입력해주세요");
        }

        return amount;
    }

    public static int readManualLottoCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        int count = Integer.parseInt(SCANNER.nextLine());

        if (count < 0) {
            throw new IllegalArgumentException("로또 수는 0 미만이 될 수 없습니다.");
        }

        return count;
    }

    public static List<LottoTicket> readManualLottoTickets(int count) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");

        List<LottoTicket> tickets = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            List<LottoNumber> lottoNumbers = readLottoNumbers();
            tickets.add(new LottoTicket(lottoNumbers));
        }

        return tickets;
    }

    public static List<LottoNumber> readWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return readLottoNumbers();
    }

    private static List<LottoNumber> readLottoNumbers() {
        String input = SCANNER.nextLine();

        List<Integer> winningNumbers = splitLottoNumbers(input);
        if (winningNumbers.size() != LottoTicket.LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("번호의 개수가 부족하거나 초과합니다.");
        }

        return winningNumbers.stream()
            .map(LottoNumber::new)
            .collect(Collectors.toList());
    }

    public static LottoNumber readBonusBall() {
        System.out.println("보너스 볼을 입력해 주세요.");
        int number = Integer.parseInt(SCANNER.nextLine());

        return new LottoNumber(number);
    }

    private static List<Integer> splitLottoNumbers(String numberString) {
        if (numberString.isBlank()) {
            throw new IllegalArgumentException();
        }

        return Arrays.stream(numberString.split(","))
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(Collectors.toList());
    }
}
