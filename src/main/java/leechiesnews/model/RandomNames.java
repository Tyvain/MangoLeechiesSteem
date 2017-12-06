package leechiesnews.model;

import java.util.Random;

public class RandomNames {
	static String[] names = { "Emma", "Olivia", "Sophia", "Isabella", "Mia", "Abigail", "Emily", "Charlotte", "Harper",
			"Madison", "Amelia", "Elizabeth", "Sofia", "Evelyn", "Avery", "Chloe", "Ella", "Grace", "Victoria",
			"Scarlett", "Zoey", "Addison", "Lily", "Lillian", "Natalie", "Hannah", "Aria", "Layla", "Brooklyn", "Alexa",
			"Zoe", "Penelope", "Riley", "Leah", "Audrey", "Savannah", "Allison", "Samantha", "Nora", "Skylar", "Camila",
			"Anna", "Paisley", "Ariana", "Ellie", "Aaliyah", "Claire", "Stella", "Sadie", "Mila", "Gabriella", "Lucy",
			"Emily", "Madison", "Emma", "Hannah", "Abigail", "Isabella", "Ashley", "Samantha", "Elizabeth",
			"Alexis", "Sarah", "Alyssa", "Grace", "Sophia", "Taylor", "Brianna", "Lauren", "Ava", "Kayla", "Jessica",
			"Natalie", "Chloe", "Anna", "Victoria", "Hailey", "Mia", "Sydney", "Jasmine", "Morgan", "Julia", "Destiny",
			"Rachel", "Megan", "Kaitlyn", "Katherine", "Jennifer", "Alexandra", "Haley", "Allison",
			"Maria", "Nicole", "Mackenzie", "Brooke", "Makayla", "Kaylee", "Lily", "Stephanie", "Andrea",
			"Amanda", "Katelyn", "Kimberly", "Madeline", "Gabrielle", "Zoe", "Trinity", "Alexa", "Mary", "Jenna",
			"Lillian", "Paige", "Kylie", "Gabriella", "Rebecca", "Jordan", "Sara", "Addison", "Michelle", "Riley",
			"Vanessa", "Angelina", "Leah", "Caroline", "Sofia", "Audrey", "Maya", "Avery", "Evelyn", "Autumn", "Amber",
			"Ariana", "Jocelyn", "Claire", "Jada", "Danielle", "Bailey", "Isabel", "Arianna", "Sierra", "Mariah",
			"Aaliyah", "Melanie", "Erin", "Marissa", "Jacqueline" };

	public static String getRandomName() {
		return names[new Random().nextInt(names.length)];
	}
}
