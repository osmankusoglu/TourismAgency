PGDMP                      |            tourismagency    16.1    16.1 #    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16961    tourismagency    DATABASE     �   CREATE DATABASE tourismagency WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Turkish_Turkey.1254';
    DROP DATABASE tourismagency;
                postgres    false            �            1259    16963    user    TABLE     �   CREATE TABLE public."user" (
    user_id integer NOT NULL,
    user_name text NOT NULL,
    user_pass text NOT NULL,
    user_role text NOT NULL
);
    DROP TABLE public."user";
       public         heap    postgres    false            �            1259    16962    User_user_id_seq    SEQUENCE     �   ALTER TABLE public."user" ALTER COLUMN user_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."User_user_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    216            �            1259    16971    hotel    TABLE     �  CREATE TABLE public.hotel (
    id integer NOT NULL,
    name character varying NOT NULL,
    address character varying NOT NULL,
    mail character varying NOT NULL,
    phone character varying NOT NULL,
    star character varying NOT NULL,
    car_park boolean NOT NULL,
    wifi boolean NOT NULL,
    pool boolean NOT NULL,
    fitness boolean NOT NULL,
    concierge boolean NOT NULL,
    spa boolean NOT NULL,
    room_service boolean NOT NULL
);
    DROP TABLE public.hotel;
       public         heap    postgres    false            �            1259    16970    hotel_id_seq    SEQUENCE     �   ALTER TABLE public.hotel ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.hotel_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    218            �            1259    16990    pencion    TABLE        CREATE TABLE public.pencion (
    pencion_id integer NOT NULL,
    hotel_id integer,
    pencion_type character varying(50)
);
    DROP TABLE public.pencion;
       public         heap    postgres    false            �            1259    16989    pension_pension_id_seq    SEQUENCE     �   CREATE SEQUENCE public.pension_pension_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.pension_pension_id_seq;
       public          postgres    false    220            �           0    0    pension_pension_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.pension_pension_id_seq OWNED BY public.pencion.pencion_id;
          public          postgres    false    219            �            1259    24907    room    TABLE     2  CREATE TABLE public.room (
    room_id integer NOT NULL,
    hotel_id integer NOT NULL,
    pencion_id integer NOT NULL,
    season_id integer NOT NULL,
    room_type text NOT NULL,
    room_stock integer NOT NULL,
    room_adult_price integer NOT NULL,
    room_child_price integer NOT NULL,
    room_bed_capacity integer NOT NULL,
    room_square_meter integer NOT NULL,
    room_television boolean NOT NULL,
    room_minibar boolean NOT NULL,
    room_game_console boolean NOT NULL,
    room_cash_box boolean NOT NULL,
    room_projection boolean NOT NULL
);
    DROP TABLE public.room;
       public         heap    postgres    false            �            1259    24906    room_room_id_seq    SEQUENCE     �   ALTER TABLE public.room ALTER COLUMN room_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.room_room_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    224            �            1259    17002    season    TABLE     �   CREATE TABLE public.season (
    season_id integer NOT NULL,
    hotel_id integer,
    season_strt_date date,
    season_fnsh_date date
);
    DROP TABLE public.season;
       public         heap    postgres    false            �            1259    17001    season_season_id_seq    SEQUENCE     �   CREATE SEQUENCE public.season_season_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.season_season_id_seq;
       public          postgres    false    222            �           0    0    season_season_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.season_season_id_seq OWNED BY public.season.season_id;
          public          postgres    false    221            .           2604    16993    pencion pencion_id    DEFAULT     x   ALTER TABLE ONLY public.pencion ALTER COLUMN pencion_id SET DEFAULT nextval('public.pension_pension_id_seq'::regclass);
 A   ALTER TABLE public.pencion ALTER COLUMN pencion_id DROP DEFAULT;
       public          postgres    false    219    220    220            /           2604    17005    season season_id    DEFAULT     t   ALTER TABLE ONLY public.season ALTER COLUMN season_id SET DEFAULT nextval('public.season_season_id_seq'::regclass);
 ?   ALTER TABLE public.season ALTER COLUMN season_id DROP DEFAULT;
       public          postgres    false    222    221    222            �          0    16971    hotel 
   TABLE DATA           �   COPY public.hotel (id, name, address, mail, phone, star, car_park, wifi, pool, fitness, concierge, spa, room_service) FROM stdin;
    public          postgres    false    218   .)       �          0    16990    pencion 
   TABLE DATA           E   COPY public.pencion (pencion_id, hotel_id, pencion_type) FROM stdin;
    public          postgres    false    220   M*       �          0    24907    room 
   TABLE DATA           �   COPY public.room (room_id, hotel_id, pencion_id, season_id, room_type, room_stock, room_adult_price, room_child_price, room_bed_capacity, room_square_meter, room_television, room_minibar, room_game_console, room_cash_box, room_projection) FROM stdin;
    public          postgres    false    224   �*       �          0    17002    season 
   TABLE DATA           Y   COPY public.season (season_id, hotel_id, season_strt_date, season_fnsh_date) FROM stdin;
    public          postgres    false    222   W+       �          0    16963    user 
   TABLE DATA           J   COPY public."user" (user_id, user_name, user_pass, user_role) FROM stdin;
    public          postgres    false    216   �+       �           0    0    User_user_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public."User_user_id_seq"', 57, true);
          public          postgres    false    215            �           0    0    hotel_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.hotel_id_seq', 14, true);
          public          postgres    false    217            �           0    0    pension_pension_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.pension_pension_id_seq', 37, true);
          public          postgres    false    219            �           0    0    room_room_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.room_room_id_seq', 24, true);
          public          postgres    false    223            �           0    0    season_season_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.season_season_id_seq', 22, true);
          public          postgres    false    221            1           2606    16967    user User_pkey 
   CONSTRAINT     U   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT "User_pkey" PRIMARY KEY (user_id);
 <   ALTER TABLE ONLY public."user" DROP CONSTRAINT "User_pkey";
       public            postgres    false    216            3           2606    16977    hotel hotel_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.hotel
    ADD CONSTRAINT hotel_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.hotel DROP CONSTRAINT hotel_pkey;
       public            postgres    false    218            5           2606    16995    pencion pension_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.pencion
    ADD CONSTRAINT pension_pkey PRIMARY KEY (pencion_id);
 >   ALTER TABLE ONLY public.pencion DROP CONSTRAINT pension_pkey;
       public            postgres    false    220            9           2606    24911    room room_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pkey PRIMARY KEY (room_id);
 8   ALTER TABLE ONLY public.room DROP CONSTRAINT room_pkey;
       public            postgres    false    224            7           2606    17007    season season_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.season
    ADD CONSTRAINT season_pkey PRIMARY KEY (season_id);
 <   ALTER TABLE ONLY public.season DROP CONSTRAINT season_pkey;
       public            postgres    false    222            :           2606    16996    pencion pension_hotel_id_fkey    FK CONSTRAINT     }   ALTER TABLE ONLY public.pencion
    ADD CONSTRAINT pension_hotel_id_fkey FOREIGN KEY (hotel_id) REFERENCES public.hotel(id);
 G   ALTER TABLE ONLY public.pencion DROP CONSTRAINT pension_hotel_id_fkey;
       public          postgres    false    4659    220    218            ;           2606    17008    season season_hotel_id_fkey    FK CONSTRAINT     {   ALTER TABLE ONLY public.season
    ADD CONSTRAINT season_hotel_id_fkey FOREIGN KEY (hotel_id) REFERENCES public.hotel(id);
 E   ALTER TABLE ONLY public.season DROP CONSTRAINT season_hotel_id_fkey;
       public          postgres    false    222    218    4659            �     x��P�N�0�_��3��&)eM@�HB���:%�#�E!?�ؕ,L���8q���g���Nw�b��L��;M9$�D�..��+r�A�"1 /̃0 UU����9�!�W�v|n����Z�jV][S���oHx�)���f�Df�R?��I<8��R̔�еݮk٨��ȃ��`���z��7�ٚu�f}2���vf��%�n'^qjg��Dȭ<��g䜪���YO�Ӂ�W}���L��c�k$�e*$%�G]����b9��܀�D��{������      �   q   x�36�44��))JT�H-R8:?�R�%1#3��ؘ�И]Є�Ј3$1W! 1�8�2?��ؔ��M�$�Xtd#��9���cNv~��GbQ���
n�99
�E�)�%\1z\\\ a�,1      �   y   x�]��
�@�ϓ�)������Z𤥠-�'K*C�e�D�+��<�c�_04U�ao<(�8���O?@eR���_�>%0C� ���ڱ,������SLy�v��D�5"��,M      �   E   x�M���0��.A��R؅�� �P�;��:B=L���c������֞+�#h��Ğ����]��O�      �   P   x�3�LL���C&��8Ss`8'�25�����dp��qFޖ�Z����ihdlb
5�Ԝӻ4''1����#ARM1z\\\ �z*%     