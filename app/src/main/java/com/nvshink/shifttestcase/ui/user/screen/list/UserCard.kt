package com.nvshink.shifttestcase.ui.user.screen.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.nvshink.domain.user.model.UserModel
import com.nvshink.shifttestcase.R

@Composable
fun UserCard(
    user: UserModel,
    onCardClick: () -> Unit
) {
    Card(onClick = onCardClick) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .size(84.dp)
                    .padding(8.dp)
                    .clip(
                        MaterialTheme.shapes.medium
                    )
                    .background(MaterialTheme.colorScheme.surfaceDim)
            ) {
                SubcomposeAsyncImage(
                    model = user.picture.medium,
                    contentDescription = null,
                    modifier = Modifier.size(84.dp)
                )
            }
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    "${user.name.first} ${user.name.last}",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "${user.location.country}, ${user.location.city}, " +
                            "${user.location.street.name} " +
                            "${  stringResource(R.string.user_location_street_short)} " +
                            "№${user.location.street.number}",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "${stringResource(R.string.user_phone_short)}: ${user.phone}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}