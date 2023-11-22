package net.GameV;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import net.GameV.Card.Rank;
import net.GameV.Card.Suit;

public final class TwentyOne {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Создаем колоду карт и перемешиваем её
		ArrayDeque<Card> cards = new ArrayDeque<>(Suit.values().length * Rank.values().length);
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				cards.add(new Card(rank, suit));
			}
		}
		shuffle(cards);

		// Инициализируем счета игрока и искусственного интеллекта
		int playerScore = 0;
		int aiScore = 0;

		// Начинаем цикл игры
		while (true) {
			System.out.println("\nВаш счет: " + playerScore);

			System.out.print("Хотите взять еще карту? (y/n): ");
			String n = scanner.next();
			if (n.equals("y")) { // Игрок берет карту из колоды
				Card card = cards.poll();
				int cardValue = card.getRank().getPoints();
				playerScore += cardValue;
				System.out.println("Вы взяли карту: ");
				System.out.println(card.getTexture());

			} else if (n.equals("n")) { // Игрок завершает свой ход
				break;

			} else {
				System.out.println("Некорректный ввод. Попробуйте еще раз.");

			}

			// Проверяем, не превысил ли игрок сумму очков 21
			if (playerScore > 21) {
				System.out.println("\nВаш счет: " + playerScore);
				System.out.println("Вы проиграли! Ваш счет превысил 21.");
				scanner.close();
				return;
			}

		}

		scanner.close();

		System.out.println("\nВаш счет: " + playerScore);
		System.out.println("Ход соперника...");

		// Ход "искусственного интеллекта"
		while (aiScore < 17) {
			Card card = cards.poll();
			int cardValue = card.getRank().getPoints();
			aiScore += cardValue;
			System.out.println("\nСоперник взяли карту: ");
			System.out.println(card.getTexture());
			try {
				// Задержка для попытки создать видимость "раздумного"
				// искусственного интеллекта
				Thread.sleep(400 + new Random().nextInt(500));
			} catch (InterruptedException e) {}
		}

		System.out.println("\nВаш счет: " + playerScore);
		System.out.println("Счет соперника: " + aiScore);

		// Определение победителя или объявление ничьей
		if (aiScore > 21 || playerScore > aiScore) {
			System.out.println("\nВы выиграли!");
		} else if (playerScore == aiScore) {
			System.out.println("Ничья!");
		} else {
			System.out.println("Вы проиграли. Соперник набрал больше очков.");
		}

	}

	private static <T> void shuffle(ArrayDeque<T> arrayDeque) {
		List<T> list = new ArrayList<>(arrayDeque);
		Collections.shuffle(list);
		arrayDeque.clear();
		arrayDeque.addAll(list);
	}
}
