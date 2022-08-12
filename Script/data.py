from bs4 import BeautifulSoup
import requests
import urllib.request

url = "https://www.iiitm.ac.in/index.php/en/component/splms/?view=teachers"
page = requests.get(url=url)
# print(page.text)

soup = BeautifulSoup(page.content, "html.parser")

result = soup.find(id="splms")

details = result.find_all("img", class_="splms-person-img splms-img-responsive")

i = 1

for detail in details:
    # print(detail['src'])
    link = "https://www.iiitm.ac.in"+str(detail['src'])
    print(link)
    urllib.request.urlretrieve(f"{link}", f"faculty_{i}.jpg")
    i=i+1
    if i == 36:
        break;

# details = result.find_all("a", class_="splms-person-title")
# links = []
# teachers = []

# for detail in details:
#     # print(detail['href'])
#     link = "https://www.iiitm.ac.in"+str(detail['href'])
#     links.append(link)
#     print(link)

# for link in links:
#     teacher = []
#     page2 = requests.get(url=link)
#     soup2 = BeautifulSoup(page2.content, "html.parser")
#     result2 = soup2.find(id="splms")
#     name = result2.find_all("h3", class_="splms-person-title")
#     designation = result2.find_all("p", class_="splms-tecaher-lessons")
#     teacher.append(name)
#     teacher.append(designation)
#     temp_1 = result2.find_all("p", class_="splms-teacher-experience")
#     x = 0
#     for temp in temp_1:
#         if x == 0:
#             honour = temp
#             teacher.append(honour)
#         x = x+1

#     temp_2 = result2.find_all("p", class_="splms-person-email")
#     x = 0 
#     for temp in temp_2:
#         if x == 0:
#             office_phone = temp
#             teacher.append(office_phone)
#         elif x == 1:
#             residence_phone = temp
#             teacher.append(residence_phone)
#         elif x == 2:
#             address = temp
#             teacher.append(address)
#         elif x == 3:
#             email = temp
#             teacher.append(email)
#         x=x+1
    
#     teachers.append(teacher)
#     print(i)
#     i=i+1

# print("Writing Started...")
# with open("read.txt",'w') as file:
#     file.writelines(" %s\n" % faculty for faculty in teachers)


# print(teachers)
    
    
    
