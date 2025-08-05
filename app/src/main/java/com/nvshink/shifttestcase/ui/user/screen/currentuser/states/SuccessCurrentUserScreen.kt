package com.nvshink.shifttestcase.ui.user.screen.currentuser.states

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Male
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.nvshink.data.generic.CountryFlags
import com.nvshink.domain.user.model.UserModel
import com.nvshink.domain.utils.UserGender
import com.nvshink.shifttestcase.R
import com.nvshink.shifttestcase.ui.user.screen.currentuser.ClickableAddress
import com.nvshink.shifttestcase.ui.user.screen.currentuser.ClickableEmail
import com.nvshink.shifttestcase.ui.user.screen.currentuser.ClickablePhone
import com.nvshink.shifttestcase.ui.user.screen.currentuser.PasswordText
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

@Composable
fun SuccessCurrentUserScreen(
    modifier: Modifier = Modifier,
    user: UserModel
) {
    SelectionContainer(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.Companion
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            //Head
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(32.dp),
            ) {
                //Picture
                Box(
                    modifier = Modifier.Companion
                        .size(128.dp)
                        .clip(
                            MaterialTheme.shapes.large
                        )
                        .background(MaterialTheme.colorScheme.surfaceDim)
                ) {
                    SubcomposeAsyncImage(
                        user.picture.large, contentDescription = null, modifier = Modifier.Companion
                            .size(128.dp)
                    )
                }
                //Name
                Text(
                    text = "${user.name.title} ${user.name.first} ${user.name.last} ${
                        CountryFlags.Companion.fromCountryCode(user.nat)?.emojiCode
                            ?: CountryFlags.UN.emojiCode
                    }",
                    style = MaterialTheme.typography.headlineMedium
                )

            }
            Spacer(Modifier.size(16.dp))
            //Username
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Person, contentDescription = null)
                Text(
                    text = stringResource(R.string.user_username_long),
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Companion.Bold),

                    )
                Text(
                    text = user.username,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            //Password
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.Companion.CenterVertically
            ) {
                Icon(Icons.Default.Password, contentDescription = null)
                Text(
                    text = stringResource(R.string.user_password_long),
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Companion.Bold),
                )
                PasswordText(user.password)
            }
            //Registered
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.CalendarMonth, contentDescription = null)
                val period = Period.between(user.registered.toLocalDate(), LocalDate.now())
                Text(
                    text = stringResource(R.string.user_registered_long),
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Companion.Bold),

                    )
                Text(
                    text = "${user.registered.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))} " +
                            "(${stringResource(R.string.user_registered_with_us).lowercase()} " +
                            "${
                                periodTextFormatter(
                                    years = period.years,
                                    months = period.months,
                                    days = period.days
                                )
                            })",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            //Email
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Email, contentDescription = null)
                Text(
                    text = stringResource(R.string.user_email_long),
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Companion.Bold)
                )
                ClickableEmail(
                    email = user.email,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            //Phone
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Phone, contentDescription = null)
                Text(
                    text = stringResource(R.string.user_phone_long),
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Companion.Bold)
                )
                ClickablePhone(
                    phone = user.phone,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            //Cell
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.PhoneAndroid, contentDescription = null)
                Text(
                    text = stringResource(R.string.user_cell_long),
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Companion.Bold)
                )
                ClickablePhone(
                    phone = user.cell,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            //Gender
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    when (user.gender) {
                        UserGender.MALE -> Icons.Default.Male
                        UserGender.FEMALE -> Icons.Default.Female
                        UserGender.UNKNOWN -> Icons.Default.QuestionMark
                    }, contentDescription = null
                )
                Text(
                    text = stringResource(R.string.user_gender_long),
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Companion.Bold),

                    )
                Text(
                    text = when (user.gender) {
                        UserGender.MALE -> stringResource(R.string.user_gender_male_long)
                        UserGender.FEMALE -> stringResource(R.string.user_gender_female_long)
                        UserGender.UNKNOWN -> stringResource(R.string.user_gender_unknown_long)
                    },
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            //DOB
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Cake, contentDescription = null)
                val period = Period.between(user.dob.toLocalDate(), LocalDate.now())
                Text(
                    text = stringResource(R.string.user_date_of_birth_long),
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Companion.Bold),
                )
                Text(
                    text = "${user.dob.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))} " +
                            "(${periodTextFormatter(years = period.years)})",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            //Location
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.LocationOn, contentDescription = null)
                Text(
                    text = stringResource(R.string.user_location_long),
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Companion.Bold)
                )
                ClickableAddress(
                    address = "${user.location.country}, ${user.location.city}, " +
                            "${user.location.street.name} " +
                            "${stringResource(R.string.user_location_street_short)} " +
                            "№${user.location.street.number}",
                    latitude = user.location.coordinates.latitude.toDouble(),
                    longitude = user.location.coordinates.longitude.toDouble(),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Composable
fun periodTextFormatter(years: Int = 0, months: Int = 0, days: Int = 0): String {
    var dateString = ""
    dateString += if (years > 0) {
        "$years " + when (years.toString().last().digitToInt()) {
            1 -> if(years == 11) stringResource(R.string.user_date_years_ru).lowercase() else stringResource(R.string.user_date_year).lowercase()
            in 2..4 -> if(years in 12..14) stringResource(R.string.user_date_years_ru).lowercase() else stringResource(R.string.user_date_years).lowercase()
            else -> stringResource(R.string.user_date_years_ru).lowercase()
        }
    } else ""
    dateString += if (months > 0) {
        " ${
            "$months " + when (months.toString().last().digitToInt()) {
                1 -> if(months == 11) stringResource(R.string.user_date_months_ru).lowercase() else stringResource(R.string.user_date_month).lowercase()
                in 2..4 -> if(months in 12..14) stringResource(R.string.user_date_months_ru).lowercase() else stringResource(R.string.user_date_months).lowercase()
                else -> stringResource(R.string.user_date_months_ru).lowercase()
            }
        } "
    } else ""
    dateString += if (days > 0) {
        "$days " + when (days.toString().last().digitToInt()) {
            1 -> if(days == 11) stringResource(R.string.user_date_days_ru).lowercase() else stringResource(R.string.user_date_day).lowercase()
            in 2..4 -> if(days in 12..14) stringResource(R.string.user_date_days_ru).lowercase() else stringResource(R.string.user_date_days).lowercase()
            else -> stringResource(R.string.user_date_days_ru).lowercase()
        }
    } else ""
    return dateString
}