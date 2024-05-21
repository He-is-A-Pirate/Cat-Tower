package heispirate.cattower.domain.admin.model

enum class Role {
    DAILY_LIFE_MANAGER, // 일상 게시판 관리자
    INFORMATION_MANAGER, // 정보 게시판 관리자
    QUESTION_MANAGER, // 질문 게시판 관리자
    SHARING_MANAGER, // 나눔 게시판 관리자
    ADMIN // 모든 게시판 및 기능을 관리하는 관리자
}