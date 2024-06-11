package heispirate.cattower.domain.post.model

import heispirate.cattower.infra.category.Category

enum class SubCategory(val category: Category) {
    // DailyLife
    GOOD_THINGS(Category.DAILY_LIFE), // 좋은일
    BAD_THINGS(Category.DAILY_LIFE), // 나쁜일
    CHAT(Category.DAILY_LIFE), // 잡담
    SHOW_LOVE(Category.DAILY_LIFE), // 이뻐해주세요

    // Information
    FOOD_INFO(Category.INFORMATION), // 먹이
    SNACKS_INFO(Category.INFORMATION), // 간식
    HEALTH_INFO(Category.INFORMATION), // 건강
    CARE_INFO(Category.INFORMATION), // 관리
    PLAY_INFO(Category.INFORMATION), // 놀이
    TRAINING_INFO(Category.INFORMATION), // 교육

    // Question
    FOOD_QUESTION(Category.QUESTION), // 먹이
    SNACKS_QUESTION(Category.QUESTION), // 간식
    HEALTH_QUESTION(Category.QUESTION), // 건강
    CARE_QUESTION(Category.QUESTION), // 관리
    PLAY_QUESTION(Category.QUESTION), // 놀이
    TRAINING_QUESTION(Category.QUESTION), // 교육

    // Sharing
    SUPPLIES(Category.SHARING), // 용품
}