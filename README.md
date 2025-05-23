# mingyun_backend

# tag ( String -> List<>)

TEST

1. http:://localhost:8080/login

Post

{
    "username" : "mytest@naver.com",
    "password" : "mytest1!"
}

Header -> Add Token (Authorization, Bearer)

2.

http://localhost:8080/articles/add


TEST JSON
{
  "id": 1,
  "title": "테스트 제목입니다",
  "content": "이건 테스트 내용입니다.",
  "createTime": "2025-05-21T19:40:00",

  "latitude": 37.123456,
  "longitude": 127.987654,
  "placeImageURL": "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT-2cpKDTU41XQ8eFhvTd9P4PuXUOvwDAAK8g&s",
  "rating": 4,
  "placeId": 1,

  "username": "mytest@naver.com",

  "tags": ["운동", "한강", "철봉"]
}


.




