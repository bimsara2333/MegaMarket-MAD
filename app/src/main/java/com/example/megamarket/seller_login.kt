package com.example.megamarket

/*class seller_login : AppCompatActivity() {

   /* private lateinit var binding: ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.loginbtn.setOnClickListener {

            val sellerName=binding.signemail.text.toString()
            val pass=binding.enterpw.text.toString()

            firebaseAuth.signInWithEmailAndPassword(sellerName,pass).addOnCompleteListener {
                if(it.isSuccessful){
                    Toast.makeText(this,"Login Sucsess", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, SellerProfile::class.java)
                    startActivity(intent)

                }else{
                    Toast.makeText(this,"Login Fail"+it.exception.toString(),Toast.LENGTH_SHORT).show()
                }
            }

        }




    }
}*/