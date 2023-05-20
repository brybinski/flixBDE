from faker import Faker
import random
import requests
import json
from faker.providers import BaseProvider


class GenereProvider(BaseProvider):
    def movie_genre(self):
        return random.choice(['Documentary', 'Thriller', 'Mystery', 'Horror', 'Action', 'Comedy', 'Drama', 'Romance'])


fake = Faker()
fake.add_provider(GenereProvider)

amt = 10
# film
for i in range(0, amt):
    film = {}
    endpoint='http://localhost:8080/api/film?'
    film['title'] = f'\'{fake.bs()}\''
    film['duration'] = random.randint(2000, 7200)
    film['description'] = f'\'{fake.paragraph(nb_sentences=4)}\''
    film['poster'] = "./"
    film['director'] = f'\'{fake.name()}\''
    film['genreTags'] = fake.movie_genre()
    endp = endpoint
    for j in film:
        endp += f'{j}={film[j]}&'
    # print(str[:-1])
    r = requests.get(endp[:-1])
    # print(r.json)

# user
# userlist = []
#
# for i in range(0, amt):
#     user = {}
#     endpoint='http://localhost:8080/user?'
#     user['mail'] = fake.email()
#     user['passwd'] = fake.sha256()
#     endp = endpoint
#     for j in user:
#         endp += f'{j}={user[j]}&'
#     r = requests.get(endp[:-1])
#     # print(json.loads(r.text)['id'])
#     userlist.append(json.loads(r.text)['id'])
#
# # card
#
# for usr_id in userlist:
#     card = {}
#     endp = 'http://localhost:8080/card?'
#     card['user'] = usr_id
#     card['card_number'] = random.randint(1000000000000000 , 7777777777777777)
#     card['cvv'] = random.randint(100, 999)
#     card['expire_date'] = '20/22'
#     card['cardHolder'] = fake.name()
#
#     for j in card:
#         endp += f'{j}={card[j]}&'
#     r = requests.get(endp[:-1])
#     # print(r.content)

# Series
series_list = []
for i in range(0, amt):
    data = {}
    endp = 'http://localhost:8080/api/series-create?'
    data['title'] = f'\'{fake.bs()}\''
    data['dscrp'] = f'\'{fake.paragraph(nb_sentences=4)}\''
    data['poster'] = f'./'
    data['direc'] = fake.name()
    data['dur'] = random.randint(2000, 72000)

    for j in data:
        endp += f'{j}={data[j]}&'
    r = requests.get(endp[:-1])
    series_list.append(json.loads(r.text)['id'])

# season
season_list = []
for ser in series_list:
    for i in range(1,4):
        data = {}
        endp = 'http://localhost:8080/api/season-create?'
        data['series'] = ser
        data['num'] = i
        data['dscrp'] = f'\'{fake.paragraph(nb_sentences=4)}\''

        for j in data:
            endp += f'{j}={data[j]}&'
        r = requests.get(endp[:-1])
        season_list.append(json.loads(r.text)['id'])

# episode
ep_list = []
for season in season_list:
    for i in range(1,12):
        data = {}
        endp = 'http://localhost:8080/api/episode-create?'
        data['season'] = season
        data['num'] = i
        data['dur'] = random.randint(1000, 7200)
        data['dscrp'] = f'\'{fake.paragraph(nb_sentences=4)}\''
        data['path'] = f'./'


        for j in data:
            endp += f'{j}={data[j]}&'
        r = requests.get(endp[:-1])
        ep_list.append(json.loads(r.text)['id'])

# # History
# for usr in userlist:
#     for ep in ep_list[0:7]:
#         data = {}
#         endp = 'http://localhost:8080/hist-create?'
#         data['content_uuid'] = ep
#         data['acc_uuid'] = usr
#         data['episode'] = True
#         data['watchtime'] = random.randint(0, 7000)
#
#         for j in data:
#             endp += f'{j}={data[j]}&'
#         r = requests.get(endp[:-1])
#         print(r.text)

