# Turizm Acentası Yönetim Sistemi
 Bu proje, turizm acentası işlemlerini yönetmek için geliştirilmiş bir Java uygulamasını içermektedir. Proje, DAO (Data Access Object), Business, Entity, Core ve View olmak üzere beş ana paket içermektedir.

# Proje Hakkında
Turizm acentası projesi, otel yönetimi, oda rezervasyonları, müşteri bilgileri ve kullanıcı yönetimi gibi geniş bir yelpazede hizmet sunan kapsamlı bir uygulamadır. Bu proje, otel eklemeyi, odaları yönetmeyi, rezervasyon yapmayı ve müşteri bilgilerini güncellemeyi içerir. Ayrıca, kullanıcıların farklı rollerde (admin ve çalışan) oluşturulmasına olanak tanır, böylece iş süreçleri daha etkili bir şekilde yönetilebilir. Bu sayede, turizm acentası projesi, seyahat ve konaklama işlemlerini kolaylaştırarak endüstri standartlarında hizmet sunar.

# Özellikler

# Otel Yönetimi
Yeni otel ekleyebilirsiniz.
Var olan otelleri görüntüleyebilir ve düzenleyebilirsiniz.
Otel bilgilerini güncelleyebilir ve silebilirsiniz.

# Oda Yönetimi
Otele yeni oda ekleyebilirsiniz.
Var olan odaları görüntüleyebilir ve düzenleyebilirsiniz.
Oda bilgilerini güncelleyebilir ve silebilirsiniz.

# Rezervasyon Yönetimi
Rezervasyon yapabilir ve rezervasyonları görüntüleyebilirsiniz.
Rezervasyon bilgilerini güncelleyebilir ve silebilirsiniz.

# Kullanıcı Yönetimi
Yeni kullanıcı ekleyebilir ve kullanıcıları görüntüleyebilirsiniz.
Kullanıcı bilgilerini güncelleyebilir ve silebilirsiniz.
Kullanıcılara "admin" ve "employee" rolleri atayabilirsiniz.

# Örnek Kullanım Senaryoları
Yeni Otel Ekleme: Ana ekran üzerinden "Otel Ekle" butonuna tıklayarak yeni bir otel ekleyebilirsiniz. Eklenen otelleri görmek için "Otel Listesi" sekmesine gidin.

# Yeni Oda Ekleme
"Oda Ekle" sekmesinden mevcut bir otele yeni bir oda ekleyebilirsiniz.

# Rezervasyon Yapma
"Rezervasyon Yap" sekmesinden uygun oteli seçerek rezervasyon yapabilirsiniz.

# Kullanıcı Oluşturma
"Kullanıcılar" sekmesi üzerinden yeni bir kullanıcı oluşturabilir ve bu kullanıcıya "admin" veya "employee" rolü atayabilirsiniz.

# Rezervasyon Güncelleme/Silme
"Rezervasyonlar" sekmesinde var olan rezervasyonları görüntüleyebilir, güncelleyebilir veya silebilirsiniz.

# Proje Yapısı
Proje, beş ana paketten oluşmaktadır:

# dao
Veritabanı işlemlerini yöneten veri erişim nesneleri.
# business 
İş mantığı işlemlerini içeren sınıflar.
# entity 
Veritabanı tablolarını temsil eden varlık sınıfları.
# core
Temel yardımcı sınıflar ve araçlar.
# view
Kullanıcı arayüzü (UI) bileşenlerini içeren sınıflar.

# Gereksinimler
- Java JDK 8 veya üzeri
- PostgreSQL veritabanı
